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
) {
    companion object {
        /*Maybe set reasonable values or put it into ViewModel constructor*/
        val DEFAULT = MessagesState(author = "", period = "")
    }
}

internal sealed interface MessagesEvent {
    object InitializeUserData: MessagesEvent
    object FetchReceivedMessages: MessagesEvent
    object ToggleExpandIcon: MessagesEvent
}

internal sealed interface MessagesSideEffect {

}