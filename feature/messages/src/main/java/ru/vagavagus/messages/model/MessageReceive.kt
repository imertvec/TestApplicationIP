package ru.vagavagus.messages.model

data class MessageReceive(
    val index: Int,
    val timestamp: Long,
    val recipient: String,
    val text: String
)