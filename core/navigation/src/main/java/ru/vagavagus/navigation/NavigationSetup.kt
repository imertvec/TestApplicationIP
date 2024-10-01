package ru.vagavagus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavigatorProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationSetup(
    startDestination: BottomTabs,
    screens: List<ComposableScreen>
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        screens.forEach { it.composable(this, navController) }
    }
}

fun interface ComposableScreen {
    fun composable(builder: NavGraphBuilder, navController: NavController)
}