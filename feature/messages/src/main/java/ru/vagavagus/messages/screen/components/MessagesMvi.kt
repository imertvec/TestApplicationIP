package ru.vagavagus.messages.screen.components

import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.messages.model.MessageReceive
import ru.vagavagus.messages.model.MessageSend

internal data class MessagesState(
    val received: ResourceState<List<MessageReceive>> = ResourceState.Idle,
    val sent: ResourceState<List<MessageSend>> = ResourceState.Idle,
    val author: String,
    val period: String,
    val receivedExpanded: Boolean = false,
    val sentExpanded: Boolean = false,
    val recipients: ResourceState<List<String>> = ResourceState.Loading,
    val messageText: String,
) {
    companion object {
        /*Maybe set reasonable values or put it into ViewModel constructor and pass from another screen */
        val DEFAULT = MessagesState(author = "", period = "", messageText = "")
    }
}

internal sealed interface MessagesEvent {
    object InitializeUserData: MessagesEvent
    object FetchReceivedMessages: MessagesEvent
    object FetchSentMessages: MessagesEvent
    object ToggleExpandReceiveMessages: MessagesEvent
    object ToggleExpandSentMessages: MessagesEvent
    class SendClick(val recipient: String?): MessagesEvent
    class ChangeMessageText(val value: String): MessagesEvent

}

internal sealed interface MessagesSideEffect {

}