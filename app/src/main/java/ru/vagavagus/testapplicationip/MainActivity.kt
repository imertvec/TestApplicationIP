package ru.vagavagus.testapplicationip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.vagavagus.design.ui.TestApplicationIPTheme
import ru.vagavagus.devices_list.DevicesListNavigation
import ru.vagavagus.messages.MessagesNavigation
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.NavigationSetup

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestApplicationIPTheme {
                /*todo: Add new top-level into @screens*/
                NavigationSetup(
                    startDestination = BottomTabs.Messages,
                    screens = listOf(

                    )
                )
            }
        }
    }
}