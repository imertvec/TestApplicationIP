package ru.vagavagus.messages.data

import kotlinx.datetime.Clock
import ru.vagavagus.messages.repository.UserDataRepository

internal class UserDataRepositoryImpl: UserDataRepository {

    /*Return mock my name*/
    override suspend fun fetchCurrentAuthor(): String {
        return "Vladimir"
    }

    /*Return mock current UTC time*/
    override suspend fun fetchSelectedPeriod(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }
}