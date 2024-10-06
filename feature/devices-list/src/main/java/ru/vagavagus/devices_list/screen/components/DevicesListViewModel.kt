package ru.vagavagus.devices_list.screen.components

import ru.vagavagus.android_domain.orbit.OrbitViewModel
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.devices_list.model.Device
import ru.vagavagus.devices_list.model.Status
import ru.vagavagus.devices_list.repository.RemoteDevicesRepository

internal class DevicesListViewModel(
    private val remoteDevicesRepository: RemoteDevicesRepository,

): OrbitViewModel<DevicesListState, DevicesListEvent, Any>(
    initialState = DevicesListState()
) {
    init {
        handleEvent(DevicesListEvent.FetchAvailableDevices)
    }

    override fun handleEvent(event: DevicesListEvent) {
        when(event) {
            DevicesListEvent.FetchAvailableDevices -> onFetchAvailableDevices()
            is DevicesListEvent.ChangeSelectedStatus -> onChangeSelectedStatus(event.status)
            is DevicesListEvent.DeviceClick -> onDeviceClick(event.device)
            DevicesListEvent.RetryFetchDevicesClick -> onRetryFetchDevicesClick()
        }
    }

    private fun onFetchAvailableDevices() = intent {
        reduce { state.copy(devices = ResourceState.Loading) }
        val devices = remoteDevicesRepository.fetchAllDevicesByStatus(state.selectedStatus)
        reduce { state.copy(devices = devices) }
    }

    private fun onChangeSelectedStatus(status: Status?) = intent {
        if(status != state.selectedStatus) {
            reduce { state.copy(devices = ResourceState.Loading, selectedStatus = status, selectedDevice = null) }
            val updatedDevices = remoteDevicesRepository.fetchAllDevicesByStatus(status)
            reduce { state.copy(devices = updatedDevices) }
        }
    }

    private fun onDeviceClick(device: Device) = intent {
        val newDevice = if(state.selectedDevice != device) device else null
        reduce { state.copy(selectedDevice = newDevice) }
    }

    private fun onRetryFetchDevicesClick() {
        handleEvent(DevicesListEvent.FetchAvailableDevices)
        /*Maybe here another logic...*/
    }

}