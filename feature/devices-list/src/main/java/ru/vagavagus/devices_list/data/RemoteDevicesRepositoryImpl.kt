package ru.vagavagus.devices_list.data

import kotlinx.coroutines.delay
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.devices_list.model.Device
import ru.vagavagus.devices_list.model.Status
import ru.vagavagus.devices_list.repository.RemoteDevicesRepository

class RemoteDevicesRepositoryImpl: RemoteDevicesRepository {

    /*Mock devices with test delay*/
    override suspend fun fetchAllDevicesByStatus(status: Status?): ResourceState<List<Device>> {
        val device1 = Device("Cam-5", "Camera", Status.Live, "fe:fe:fe:fe:fe:fe", "SM-03")
        val device2 = Device("Rep-1", "Camera", Status.Dead, "fe:fe:fe:fe:fe:fe", "SM-03")
        val device3 = Device("LD-4", "LiveData", Status.Mute, "fe:fe:fe:fe:fe:fe", "SM-03")
        val device4 = Device("Dmitrii", "Participant", Status.Approved, "fe:fe:fe:fe:fe:fe", "no")

        delay(1000)
        val items = listOf(device1, device2, device3, device4)
        if(status == null) return ResourceState.Success(items)

        return ResourceState.Success(items.filter { it.status == status })
    }
}