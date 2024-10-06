package ru.vagavagus.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.ComposableScreen
import ru.vagavagus.navigation.NavigationSetup
import ru.vagavagus.root.bottom_bar.RootBottomBar
import ru.vagavagus.root.topapp_bar.RootTopAppBar

@Composable
fun RootComposable(
    screens: List<ComposableScreen>
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination
    val currentTab = BottomTabs.entries.firstOrNull { it::class.qualifiedName == currentDestination?.route }
        ?: BottomTabs.StartDestination

    Scaffold(
        topBar = { RootTopAppBar(currentTab.title) },
        bottomBar = {
            RootBottomBar(
                currentTab = currentTab,
                onNavigate = navController::navigate
            )
        }
    ) { scaffoldPaddings ->
        NavigationSetup(
            modifier = Modifier.padding(scaffoldPaddings),
            navController = navController,
            startDestination = BottomTabs.StartDestination,
            screens = screens
        )
    }
}