package ru.vagavagus.messages.screen.components

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import ru.vagavagus.android_domain.orbit.OrbitViewModel
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.messages.model.MessageReceive
import ru.vagavagus.messages.repository.RemoteMessagesRepository
import ru.vagavagus.messages.repository.UserDataRepository

internal class MessagesViewModel(
    private val userDataRepository: UserDataRepository,
    private val remoteMessagesRepository: RemoteMessagesRepository,
): OrbitViewModel<MessagesState, MessagesEvent, MessagesSideEffect>(
    initialState = MessagesState.DEFAULT
) {
    init {
        handleEvent(MessagesEvent.InitializeUserData)
    }

    override fun handleEvent(event: MessagesEvent) {
        when(event) {
            MessagesEvent.InitializeUserData -> onInitializeUserData()
            MessagesEvent.FetchReceivedMessages -> onFetchReceivedMessages()
            MessagesEvent.ToggleExpandReceiveMessages -> onToggleExpandReceiveMessages()
            MessagesEvent.FetchSentMessages -> onFetchSentMessages()
            MessagesEvent.ToggleExpandSentMessages -> onToggleExpandSentMessages()
            is MessagesEvent.ChangeMessageText -> onChangeMessageText(event.value)
            is MessagesEvent.SendClick -> onSendClick(event.recipient)
            MessagesEvent.RetryReceiveRecipients -> onRetryReceiveRecipients()
        }
    }

    private fun onInitializeUserData() = intent {
        val periodUtc = userDataRepository.fetchSelectedPeriod()
        val author = userDataRepository.fetchCurrentAuthor()
        val availableRecipients = remoteMessagesRepository.fetchAvailableRecipients()

        val localFormattedPeriod = Instant
            .fromEpochMilliseconds(periodUtc)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .format(
                LocalDate.Format {
                    dayOfMonth()
                    char('.')
                    monthNumber()
                    char('.')
                    year()
                }
            )

        reduce {
            state.copy(
                author = author,
                period = localFormattedPeriod,
                recipients = availableRecipients
            )
        }
    }

    private fun onFetchReceivedMessages() = intent {
        reduce { state.copy(received = ResourceState.Loading) }
        val items = remoteMessagesRepository.fetchReceivedMessages()
        reduce { state.copy(received = items, receivedExpanded = true) }
    }

    private fun onToggleExpandReceiveMessages() = intent {
        reduce { state.copy(receivedExpanded = !state.receivedExpanded) }
    }

    private fun onFetchSentMessages() = intent {
        reduce { state.copy(sent = ResourceState.Loading) }
        val items = remoteMessagesRepository.fetchSentMessages()
        reduce { state.copy(sent = items, sentExpanded = true) }
    }

    private fun onToggleExpandSentMessages() = intent {
        reduce { state.copy(sentExpanded = !state.sentExpanded) }
    }

    private fun onChangeMessageText(value: String) = blockingIntent {
        reduce { state.copy(messageText = value) }
    }

    private fun onSendClick(recipient: String) = intent {
        val message = MessageReceive(
            timestamp = Clock.System.now().toEpochMilliseconds(),
            recipient = recipient,
            text = state.messageText
        )

        remoteMessagesRepository.sendMessage(message)
            .onSuccess { handleEvent(MessagesEvent.FetchSentMessages) }
            .onFailure { postSideEffect(MessagesSideEffect.ShowErrorSendToast) }
    }

    private fun onRetryReceiveRecipients() = intent {
        reduce { state.copy(recipients = ResourceState.Loading) }
        val availableRecipients = remoteMessagesRepository.fetchAvailableRecipients()
        reduce { state.copy(recipients = availableRecipients) }
    }
}