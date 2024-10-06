package ru.vagavagus.root.bottom_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.vagavagus.navigation.BottomTabs

@Composable
internal fun RootBottomBar(
    currentTab: BottomTabs,
    onNavigate: (BottomTabs) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomTabs.entries.forEach { tab ->
            RootBottomBarItem(
                selected = tab == currentTab,
                tab = tab,
                onClick = { if(currentTab != tab) onNavigate(tab) }
            )
        }
    }
}