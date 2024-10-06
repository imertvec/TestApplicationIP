package ru.vagavagus.messages.repository

import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.messages.model.MessageReceive
import ru.vagavagus.messages.model.MessageSend

interface RemoteMessagesRepository {
    suspend fun fetchReceivedMessages(): ResourceState<List<MessageSend>>
    suspend fun fetchSentMessages(): ResourceState<List<MessageReceive>>
    suspend fun fetchAvailableRecipients(): ResourceState<List<String>>

    /*Returns @Result<Unit> for simple check success/failure action*/
    suspend fun sendMessage(messageReceive: MessageReceive): Result<Int>
}