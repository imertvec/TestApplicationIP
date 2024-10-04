package ru.vagavagus.messages.model

data class MessageSend(
    val index: Int,
    val timestamp: Long,
    val author: String,
    val text: String
)
