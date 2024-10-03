package ru.vagavagus.devices_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.ComposableScreen

object DevicesListNavigation: ComposableScreen {
    override fun composable(builder: NavGraphBuilder, navController: NavController) {
        builder.composable<BottomTabs.DevicesList> {
            /*Empty screen*/
        }
    }
}