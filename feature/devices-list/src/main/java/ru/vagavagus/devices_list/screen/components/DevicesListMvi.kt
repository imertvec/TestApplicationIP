package ru.vagavagus.devices_list.screen.components

import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.devices_list.model.Device
import ru.vagavagus.devices_list.model.Status

data class DevicesListState(
    val selectedStatus: Status? = null,
    val devices: ResourceState<List<Device>> = ResourceState.Loading,
    val selectedDevice: Device? = null,
)

sealed interface DevicesListEvent {
    object FetchAvailableDevices: DevicesListEvent
    class ChangeSelectedStatus(val status: Status?): DevicesListEvent
    object RetryFetchDevicesClick: DevicesListEvent
    class DeviceClick(val device: Device): DevicesListEvent
}