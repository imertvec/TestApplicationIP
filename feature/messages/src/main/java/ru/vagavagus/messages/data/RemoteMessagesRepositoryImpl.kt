package ru.vagavagus.messages.data

import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import ru.vagavagus.messages.model.MessageReceive
import ru.vagavagus.messages.model.MessageSend
import ru.vagavagus.messages.repository.RemoteMessagesRepository

internal class RemoteMessagesRepositoryImpl: RemoteMessagesRepository {

    /*Mock data with test delay(2000)*/
    override suspend fun fetchReceivedMessages(): List<MessageReceive> {
        val message1 = MessageReceive(0, Clock.System.now().plus(1, DateTimeUnit.DAY, TimeZone.UTC).toEpochMilliseconds(), "Author1", "Hello, World!")
        val message2 = MessageReceive(1, Clock.System.now().plus(2, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author2", "Kotlin is awesome")
        val message3 = MessageReceive(2, Clock.System.now().plus(3, DateTimeUnit.HOUR, TimeZone.UTC).toEpochMilliseconds(), "Author3", "Testing frameworks")
        val message4 = MessageReceive(3, Clock.System.now().plus(4, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author4", "Unit testing")
        val message5 = MessageReceive(4, Clock.System.now().plus(2, DateTimeUnit.WEEK, TimeZone.UTC).toEpochMilliseconds(), "Author5", "Integration testing")
        delay(2000)

        return listOf(message1, message2, message3, message4, message5)
    }

    /*Mock data with test delay(2000)*/
    override suspend fun fetchSentMessages(): List<MessageSend> {
        val message1 = MessageSend(0, Clock.System.now().plus(1, DateTimeUnit.DAY, TimeZone.UTC).toEpochMilliseconds(), "Author1", "Hello, World!")
        val message2 = MessageSend(1, Clock.System.now().plus(2, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author2", "Kotlin is awesome")
        val message3 = MessageSend(2, Clock.System.now().plus(3, DateTimeUnit.HOUR, TimeZone.UTC).toEpochMilliseconds(), "Author3", "Testing frameworks")
        val message4 = MessageSend(3, Clock.System.now().plus(4, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author4", "Unit testing")
        val message5 = MessageSend(4, Clock.System.now().plus(2, DateTimeUnit.WEEK, TimeZone.UTC).toEpochMilliseconds(), "Author5", "Integration testing")
        delay(3000)

        return listOf(message1, message2, message3, message4, message5)
    }

    /*Mock 5 authors*/
    override suspend fun fetchAvailableRecipients(): List<String> {
        return listOf("Author1", "Author2", "Author3", "Author4", "Author5")
    }

    override suspend fun sendMessage(messageSend: MessageSend): Result<Unit> {
        /*We send our message somewhere and return the mock result*/
        return Result.success(Unit)
    }
}