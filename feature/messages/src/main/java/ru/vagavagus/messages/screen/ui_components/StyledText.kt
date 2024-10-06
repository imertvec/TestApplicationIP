package ru.vagavagus.messages.screen.ui_components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
internal fun StyledText(
    @StringRes resId: Int,
    styledText: String,
    accentColor: Color = MaterialTheme.colorScheme.tertiary,
    separator: String = ": "
) {
    Text(
        text = buildAnnotatedString {
            append(stringResource(id = resId))
            append(separator)
            withStyle(SpanStyle(color = accentColor)) {
                append(styledText)
            }
        },
        style = MaterialTheme.typography.bodyMedium
    )
}
