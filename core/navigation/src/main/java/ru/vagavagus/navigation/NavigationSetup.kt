package ru.vagavagus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.vagavagus.navigation.transitions.ScreenEnterTransition
import ru.vagavagus.navigation.transitions.ScreenExitTransition

@Composable
fun NavigationSetup(
    navController: NavHostController,
    startDestination: BottomTabs,
    screens: List<ComposableScreen>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { ScreenEnterTransition },
        exitTransition = { ScreenExitTransition }
    ) {
        screens.forEach { it.composable(this, navController) }
    }
}

fun interface ComposableScreen {
    fun composable(builder: NavGraphBuilder, navController: NavController)
}