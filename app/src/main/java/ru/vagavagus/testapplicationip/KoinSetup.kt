package ru.vagavagus.testapplicationip

import org.koin.core.context.startKoin
import ru.vagavagus.messages.di.FeatureMessagesModule

internal fun koinSetup() {
    startKoin {
        modules(
            FeatureMessagesModule.module,
        )
    }
}