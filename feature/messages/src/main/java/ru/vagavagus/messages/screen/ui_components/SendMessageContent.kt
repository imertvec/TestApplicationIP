package ru.vagavagus.messages.screen.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.feature.messages.R

@Composable
internal fun SendMessageContent(
    messageText: String,
    recipientsState: ResourceState<List<String>>,
    onSendClick: (recipient: String) -> Unit,
    onChangeText: (newText: String) -> Unit,
    onRetryReceiveRecipients: () -> Unit
) {
    var selectedRecipient: String? by remember { mutableStateOf(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Text(
                text = stringResource(id = R.string.action_label_new),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            DropDownMenuRecipients(
                selectedRecipient = selectedRecipient,
                recipientsState = recipientsState,
                onRecipientPick = { selectedRecipient = it },
                onRetryReceiveRecipients = onRetryReceiveRecipients
            )

            MessageTextField(
                messageText = messageText,
                onChangeText = onChangeText
            )

            val focusManager = LocalFocusManager.current
            Button(
                onClick = {
                    onSendClick(selectedRecipient!!)
                    focusManager.clearFocus()
                },
                enabled = messageText.isNotEmpty() && selectedRecipient != null,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(text = stringResource(id = R.string.button_send))
            }
        }
    }
}

@Composable
private fun DropDownMenuRecipients(
    selectedRecipient: String?,
    recipientsState: ResourceState<List<String>>,
    onRecipientPick: (String) -> Unit,
    onRetryReceiveRecipients: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }

    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.primary,
        LocalTextStyle provides MaterialTheme.typography.bodyMedium
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium, Alignment.Start)
        ) {
            /*Maybe put separator to strings.xml or const*/
            Text(text = stringResource(id = R.string.recipient_title) + ":")

            when(recipientsState) {
                ResourceState.Idle -> { /*No action*/ }
                ResourceState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.size(MaterialTheme.spacing.medium)
                )
                is ResourceState.Error -> {
                    Text(text = stringResource(id = R.string.recipients_fetch_error))
                    IconButton(
                        modifier = Modifier.size(MaterialTheme.spacing.medium),
                        onClick = onRetryReceiveRecipients
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )
                    }
                }
                is ResourceState.Success -> Box {
                    if(recipientsState.data.isNotEmpty()) {
                        TextButton(
                            onClick = { isDropDownExpanded = true },
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            /*Take first if not selected*/
                            val recipient = selectedRecipient ?: recipientsState.data.first()
                            onRecipientPick(recipient)

                            Text(text = recipient)
                        }

                        DropdownMenu(
                            expanded = isDropDownExpanded,
                            onDismissRequest = { isDropDownExpanded = false }
                        ) {
                            recipientsState.data.forEach { recipient ->
                                DropdownMenuItem(
                                    text = { Text(text = recipient) },
                                    onClick = {
                                        onRecipientPick(recipient)
                                        isDropDownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MessageTextField(
    messageText: String,
    onChangeText: (newText: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = messageText,
        onValueChange = onChangeText,
        label = {
            Text(
                text = stringResource(id = R.string.text_input_hint),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
        )
    )
}