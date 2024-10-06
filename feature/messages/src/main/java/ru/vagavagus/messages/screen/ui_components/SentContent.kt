package ru.vagavagus.messages.screen.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.feature.messages.R
import ru.vagavagus.messages.model.MessageSend

@Composable
fun SentContent(
    expanded: Boolean,
    itemsState: ResourceState<List<MessageSend>>,
    onLoadClick: () -> Unit,
    onToggleIcon: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            LoadSentMessagesButton(
                receivedState = itemsState,
                onLoadClick = onLoadClick,
                expanded = expanded,
                onIconClick = onToggleIcon
            )

            when(itemsState) {
                ResourceState.Idle, ResourceState.Loading -> { /*No action*/ }
                is ResourceState.Error -> AnimatedVisibility(visible = expanded) {
                    ErrorMessage(message = itemsState.errorMessage)
                }
                is ResourceState.Success -> AnimatedVisibility(expanded) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(MaterialTheme.spacing.small),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                    ) {
                        items(itemsState.data) { message ->
                            SentListItem(
                                modifier = Modifier.fillMaxWidth(),
                                message = message
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadSentMessagesButton(
    expanded: Boolean,
    receivedState: ResourceState<List<MessageSend>>,
    onLoadClick: () -> Unit,
    onIconClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onLoadClick) {
            Text(text = stringResource(id = R.string.button_load_sent))
        }

        if(receivedState is ResourceState.Loading)
            CircularProgressIndicator(modifier = Modifier.size(MaterialTheme.spacing.medium))

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        if(receivedState is ResourceState.Success) {
            val rotationValue = remember { Animatable(initialValue = 0f) }

            LaunchedEffect(expanded) {
                if(expanded)
                    rotationValue.animateTo(targetValue = 180f)
                else
                    rotationValue.animateTo(targetValue = 0f)
            }

            IconButton(onClick = onIconClick) {
                Icon(
                    modifier = Modifier.rotate(rotationValue.value),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
private fun SentListItem(
    message: MessageSend,
    modifier: Modifier = Modifier,
) {
    var isTextVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(MaterialTheme.spacing.small),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            /*Maybe move to ViewModel*/
            val localDateTime = Instant.fromEpochMilliseconds(message.timestamp).toLocalDateTime(
                TimeZone.currentSystemDefault())
            val formattedTime = localDateTime.time.format(
                LocalTime.Format {
                    hour()
                    char(':')
                    minute()
                    char(':')
                    secondFraction(2)
                }
            )
            val formattedDate = localDateTime.date.format(LocalDate.Format { byUnicodePattern("dd.MM.yyyy") })


            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onPrimary,
                LocalTextStyle provides MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
            ) {
                Text(
                    text = stringResource(id = R.string.index_sym) + "${message.index}",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                Column {
                    StyledText(
                        resId = R.string.time_title,
                        styledText = formattedTime,
                        accentColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    StyledText(
                        resId = R.string.date_title,
                        styledText = formattedDate,
                        accentColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    StyledText(
                        resId = R.string.author_title,
                        styledText = message.author,
                        accentColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }

                Spacer(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth())

                TextButton(onClick = { isTextVisible = !isTextVisible }) {
                    val textResId = if(isTextVisible) R.string.hide_text_label else R.string.show_text_label
                    Text(text = stringResource(id = textResId))
                }
            }
        }
        AnimatedVisibility(visible = isTextVisible) {
            HorizontalDivider()
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = message.text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}