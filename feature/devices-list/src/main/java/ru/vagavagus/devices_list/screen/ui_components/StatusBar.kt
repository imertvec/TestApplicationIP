package ru.vagavagus.devices_list.screen.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.devices_list.model.Status
import ru.vagavagus.feature.devicesList.R

@Composable
internal fun StatusBar(
    selectedStatus: Status?,
    onStatusClick: (Status?) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium)
    ) {
        item {
            /*`All` button*/
            StatusItem(
                isSelected = selectedStatus == null,
                status = null,
                onClick = { onStatusClick(null) }
            )
        }
        items(Status.entries) { status ->
            StatusItem(
                isSelected = status == selectedStatus,
                status = status,
                onClick = { onStatusClick(status) }
            )
        }
    }
}

@Composable
private fun StatusItem(
    isSelected: Boolean,
    status: Status?,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        border = if(!isSelected) BorderStroke(1.dp, MaterialTheme.colorScheme.primary) else null,
        shape = RoundedCornerShape(MaterialTheme.spacing.small),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isSelected) MaterialTheme.colorScheme.tertiary else Color.Transparent,
            contentColor = if(isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Unspecified
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large),
            text = status?.title ?: stringResource(id = R.string.all_label),
            style = MaterialTheme.typography.bodySmall
        )
    }
}