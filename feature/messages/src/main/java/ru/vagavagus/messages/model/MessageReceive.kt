package ru.vagavagus.messages.model

data class MessageReceive(
    val index: Int = 0,
    val timestamp: Long,
    val recipient: String,
    val text: String
)