package ru.vagavagus.testapplicationip

import org.koin.core.context.startKoin
import ru.vagavagus.devices_list.di.FeatureDevicesListModule
import ru.vagavagus.messages.di.FeatureMessagesModule

internal fun koinSetup() {
    startKoin {
        modules(
            FeatureMessagesModule.module,
            FeatureDevicesListModule.module,
        )
    }
}