package ru.vagavagus.testapplicationip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.vagavagus.devices_list.DevicesListNavigation
import ru.vagavagus.messages.MessagesNavigation
import ru.vagavagus.root.RootComposable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RootComposable(
                screens = listOf(
                    DevicesListNavigation,
                    MessagesNavigation
                )
            )
        }
    }
}