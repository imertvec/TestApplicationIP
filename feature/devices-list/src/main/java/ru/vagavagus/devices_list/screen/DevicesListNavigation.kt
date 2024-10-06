package ru.vagavagus.devices_list.screen

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import ru.vagavagus.devices_list.screen.components.DevicesListViewModel
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.ComposableScreen

object DevicesListNavigation: ComposableScreen {
    override fun composable(builder: NavGraphBuilder, navController: NavController) {
        builder.composable<BottomTabs.DevicesList> {
            val viewModel = koinViewModel<DevicesListViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            DevicesListContent(
                uiState = uiState,
                handleEvent = viewModel::handleEvent
            )
        }
    }
}