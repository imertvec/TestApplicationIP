package ru.vagavagus.messages.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.feature.messages.R
import ru.vagavagus.messages.screen.components.MessagesEvent
import ru.vagavagus.messages.screen.components.MessagesState
import ru.vagavagus.messages.screen.ui_components.ReceivedContent
import ru.vagavagus.messages.screen.ui_components.StyledText

@Composable
internal fun MessagesContent(
    uiState: MessagesState,
    handleEvent: (MessagesEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StyledText(resId = R.string.name_label, styledText = uiState.author)
            StyledText(resId = R.string.period_label, styledText = uiState.period)
        }
        HorizontalDivider()
        ReceivedContent(
            expanded = uiState.receivedExpanded,
            itemsState = uiState.received,
            onReceiveClick = { handleEvent(MessagesEvent.FetchReceivedMessages) },
            onToggleIcon = { handleEvent(MessagesEvent.ToggleExpandIcon) }
        )
    }
}