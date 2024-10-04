package ru.vagavagus.messages.repository

interface UserDataRepository {
    /*We take the data from somewhere*/
    suspend fun fetchCurrentAuthor(): String
    suspend fun fetchSelectedPeriod(): Long
}