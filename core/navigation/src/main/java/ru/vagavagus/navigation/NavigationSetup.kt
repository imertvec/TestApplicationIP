package ru.vagavagus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

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
    ) {
        screens.forEach { it.composable(this, navController) }
    }
}

fun interface ComposableScreen {
    fun composable(builder: NavGraphBuilder, navController: NavController)
}