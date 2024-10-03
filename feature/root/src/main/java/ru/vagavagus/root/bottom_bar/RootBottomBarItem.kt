package ru.vagavagus.root.bottom_bar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ru.vagavagus.navigation.BottomTabs

@Composable
internal fun RootBottomBarItem(
    selected: Boolean,
    tab: BottomTabs,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = tab.title,
            color = if(selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
        )
    }
}