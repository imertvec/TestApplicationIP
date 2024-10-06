package ru.vagavagus.messages.screen.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import ru.vagavagus.android_domain.orbit.OrbitViewModel
import ru.vagavagus.android_domain.wrappers.ResourceState
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
            MessagesEvent.ToggleExpandIcon -> onToggleExpandIcon()
        }
    }

    private fun onInitializeUserData() = intent {
        val periodUtc = userDataRepository.fetchSelectedPeriod()
        val author = userDataRepository.fetchCurrentAuthor()

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

        reduce { state.copy(author = author, period = localFormattedPeriod) }
    }

    private fun onFetchReceivedMessages() = intent {
        reduce { state.copy(received = ResourceState.Loading) }
        val items = remoteMessagesRepository.fetchReceivedMessages()
        reduce { state.copy(received = items, receivedExpanded = true) }
    }

    private fun onToggleExpandIcon() = intent {
        reduce { state.copy(receivedExpanded = !state.receivedExpanded) }
    }
}