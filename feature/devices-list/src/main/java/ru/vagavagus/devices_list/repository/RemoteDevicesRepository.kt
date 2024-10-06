package ru.vagavagus.devices_list.repository

import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.devices_list.model.Device
import ru.vagavagus.devices_list.model.Status

interface RemoteDevicesRepository {
    suspend fun fetchAllDevicesByStatus(status: Status?): ResourceState<List<Device>>
}