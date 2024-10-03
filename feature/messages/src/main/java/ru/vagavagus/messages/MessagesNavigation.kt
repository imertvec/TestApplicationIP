package ru.vagavagus.messages

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.ComposableScreen

object MessagesNavigation: ComposableScreen {
    override fun composable(builder: NavGraphBuilder, navController: NavController) {
        builder.composable<BottomTabs.Messages> {
            /*Empty screen*/
        }
    }

}