package ru.vagavagus.devices_list.model

data class Device(
    val name: String,
    val type: String,
    val status: Status,
    val mac: String,
    val subscriptions: String
)