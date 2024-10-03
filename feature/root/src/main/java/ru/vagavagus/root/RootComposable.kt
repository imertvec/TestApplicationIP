package ru.vagavagus.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.vagavagus.navigation.BottomTabs
import ru.vagavagus.navigation.ComposableScreen
import ru.vagavagus.navigation.NavigationSetup
import ru.vagavagus.root.bottom_bar.RootBottomBar

@Composable
fun RootComposable(
    screens: List<ComposableScreen>
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { RootBottomBar(navController) {
            navController.navigate(it)
        } }
    ) { scaffoldPaddings ->
        /*todo: Add new top-level into @screens*/
        NavigationSetup(
            modifier = Modifier.padding(scaffoldPaddings),
            navController = navController,
            startDestination = BottomTabs.Messages,
            screens = screens
        )
    }
}