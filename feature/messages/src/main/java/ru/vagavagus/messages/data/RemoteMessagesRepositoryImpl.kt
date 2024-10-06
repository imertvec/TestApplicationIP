package ru.vagavagus.messages.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.messages.model.MessageReceive
import ru.vagavagus.messages.model.MessageSend
import ru.vagavagus.messages.repository.RemoteMessagesRepository
import java.lang.IllegalArgumentException

internal class RemoteMessagesRepositoryImpl: RemoteMessagesRepository {
    private var someRemoteDatabase: MutableList<MessageReceive> = mutableListOf()

    /*Mock data with test delay(2000)*/
    override suspend fun fetchReceivedMessages(): ResourceState<List<MessageSend>> = withContext(Dispatchers.IO){
        val message1 = MessageSend(0, Clock.System.now().plus(1, DateTimeUnit.DAY, TimeZone.UTC).toEpochMilliseconds(), "Author1", "Hello, World!")
        val message2 = MessageSend(1, Clock.System.now().plus(2, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author2", "Kotlin is awesome")
        val message3 = MessageSend(2, Clock.System.now().plus(3, DateTimeUnit.HOUR, TimeZone.UTC).toEpochMilliseconds(), "Author3", "Testing frameworks")
        val message4 = MessageSend(3, Clock.System.now().plus(4, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author4", "Unit testing")
        val message5 = MessageSend(4, Clock.System.now().plus(2, DateTimeUnit.WEEK, TimeZone.UTC).toEpochMilliseconds(), "Author5", "Integration testingIntegration testingIntegration testingIntegration testingIntegration testingIntegration testingIntegration testing")
        delay(2000)

        val items = listOf(message1, message2, message3, message4, message5)
        ResourceState.Success(items)
    }

    /*Mock data with test delay(2000)*/
    override suspend fun fetchSentMessages(): ResourceState<List<MessageReceive>> = withContext(Dispatchers.IO){
        val message1 = MessageReceive(0, Clock.System.now().plus(1, DateTimeUnit.DAY, TimeZone.UTC).toEpochMilliseconds(), "Author1", "Hello, World!")
        val message2 = MessageReceive(1, Clock.System.now().plus(2, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author2", "Kotlin is awesome")
        val message3 = MessageReceive(2, Clock.System.now().plus(3, DateTimeUnit.HOUR, TimeZone.UTC).toEpochMilliseconds(), "Author3", "Testing frameworks")
        val message4 = MessageReceive(3, Clock.System.now().plus(4, DateTimeUnit.MINUTE, TimeZone.UTC).toEpochMilliseconds(), "Author4", "Unit testing")
        val message5 = MessageReceive(4, Clock.System.now().plus(2, DateTimeUnit.WEEK, TimeZone.UTC).toEpochMilliseconds(), "Author5", "Integration testing")
        delay(3000)

        val items = listOf(message1, message2, message3, message4, message5) + someRemoteDatabase
        ResourceState.Success(items)
    }

    /*Mock 5 authors*/
    override suspend fun fetchAvailableRecipients(): ResourceState<List<String>> = withContext(Dispatchers.IO){
        val items = listOf("Author1", "Author2", "Author3", "Author4", "Author5")
        ResourceState.Success(items)
    }


    override suspend fun sendMessage(messageReceive: MessageReceive): Result<Int> = withContext(Dispatchers.IO){
        /*We send our message somewhere and return the mock result*/
        val newIndex = someRemoteDatabase.lastOrNull()?.index?.plus(1) ?: 5
        someRemoteDatabase.add(messageReceive.copy(index = newIndex))
        Result.failure(IllegalArgumentException())
    }
}