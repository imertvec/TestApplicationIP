package ru.vagavagus.root.bottom_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.vagavagus.navigation.BottomTabs

@Composable
internal fun RootBottomBar(
    navController: NavController,
    onNavigate: (BottomTabs) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomTabs.entries.forEach { tab ->
            val tabName = tab::class.qualifiedName

            RootBottomBarItem(
                selected = tabName == currentRoute,
                tab = tab,
                onClick = { onNavigate(tab) }
            )
        }
    }
}