package ru.vagavagus.testapplicationip

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        koinSetup()
    }
}