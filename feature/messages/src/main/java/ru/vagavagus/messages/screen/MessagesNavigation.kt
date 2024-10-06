package ru.vagavagus.messages.screen

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import ru.vagavagus.feature.messages.R
import ru.vagavagus.messages.screen.components.MessagesSideEffect
import ru.vagavagus.messages.screen.components.MessagesViewModel
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.ComposableScreen

object MessagesNavigation: ComposableScreen {
    override fun composable(builder: NavGraphBuilder, navController: NavController) {
        builder.composable<BottomTabs.Messages> {
            val viewModel = koinViewModel<MessagesViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val context = LocalContext.current

            LaunchedEffect(key1 = Unit) {
                viewModel.sideEffects.collect { effect ->
                    when(effect) {
                        MessagesSideEffect.ShowErrorSendToast -> Toast.makeText(
                            context,
                            R.string.failure_send_message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            MessagesContent(
                uiState = uiState,
                handleEvent = viewModel::handleEvent
            )
        }
    }

}