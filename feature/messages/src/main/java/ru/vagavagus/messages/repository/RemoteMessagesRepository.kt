package ru.vagavagus.messages.repository

import ru.vagavagus.messages.model.MessageReceive
import ru.vagavagus.messages.model.MessageSend

interface RemoteMessagesRepository {
    suspend fun fetchReceivedMessages(): List<MessageReceive>
    suspend fun fetchSentMessages(): List<MessageSend>
    suspend fun fetchAvailableRecipients(): List<String>

    /*Returns @Result<Unit> for simple check success/failure action*/
    suspend fun sendMessage(messageSend: MessageSend): Result<Unit>
}