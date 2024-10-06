package ru.vagavagus.messages.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.vagavagus.messages.screen.components.MessagesViewModel
import ru.vagavagus.messages.data.RemoteMessagesRepositoryImpl
import ru.vagavagus.messages.data.UserDataRepositoryImpl
import ru.vagavagus.messages.repository.RemoteMessagesRepository
import ru.vagavagus.messages.repository.UserDataRepository

object FeatureMessagesModule {
    val module = module {
        factoryOf(::UserDataRepositoryImpl) bind UserDataRepository::class
        factoryOf(::RemoteMessagesRepositoryImpl) bind RemoteMessagesRepository::class

        viewModelOf(::MessagesViewModel)
    }
}