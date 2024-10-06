package ru.vagavagus.devices_list.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.vagavagus.devices_list.data.RemoteDevicesRepositoryImpl
import ru.vagavagus.devices_list.repository.RemoteDevicesRepository
import ru.vagavagus.devices_list.screen.components.DevicesListViewModel

object FeatureDevicesListModule {
    val module = module {
        factoryOf(::RemoteDevicesRepositoryImpl) bind RemoteDevicesRepository::class
        viewModelOf(::DevicesListViewModel)
    }
}