package ru.vagavagus.messages.screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import ru.vagavagus.design.extensions.spacing

@Composable
internal fun ErrorMessage(message: String) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(MaterialTheme.spacing.small),
        text = message,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
}
