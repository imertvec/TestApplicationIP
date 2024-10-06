package ru.vagavagus.devices_list.screen.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.vagavagus.android_domain.wrappers.ResourceState
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.devices_list.model.Device
import ru.vagavagus.feature.devicesList.R

@Composable
fun DevicesListBlock(
    devicesState: ResourceState<List<Device>>,
    onDeviceClick: (Device) -> Unit,
    onRetryFetchDevicesClick: () -> Unit
) {
    CompositionLocalProvider(
        LocalTextStyle provides MaterialTheme.typography.bodySmall,
        LocalContentColor provides MaterialTheme.colorScheme.primary
    ) {
        when(devicesState) {
            ResourceState.Idle -> { /*No action*/ }
            is ResourceState.Error -> DevicesErrorMessage(onRetryFetchDevicesClick)
            ResourceState.Loading -> DevicesLoader()
            is ResourceState.Success -> DevicesTable(
                devices = devicesState.data,
                onClick = { device -> onDeviceClick(device) }
            )
        }
    }
}

@Composable
private fun DevicesLoader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
    ) {
        Text(text = stringResource(id = R.string.loader_title))
        LinearProgressIndicator()
    }
}

@Composable
private fun DevicesErrorMessage(
    onRetryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.error_message),
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.size(MaterialTheme.spacing.medium),
            onClick = onRetryClick
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun DevicesTable(
    devices: List<Device>,
    onClick: (Device) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f),
    ) {
        item { DeviceListItemTitle() }
        items(devices) { device ->
            DeviceListItem(device) {
                onClick(device)
            }
            HorizontalDivider()
        }
    }
}

private const val nameWeight = .15f
private const val typeWeight = .15f
private const val statusWeight = .15f
private const val macWeight = .15f
private const val subscriptionsWeight = .25f

@Composable
private fun DeviceListItem(
    device: Device,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = MaterialTheme.spacing.extraSmall)
            .clip(RoundedCornerShape(MaterialTheme.spacing.small)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableCell(
            text = device.name,
            weight = nameWeight,
            alignment = TextAlign.Start,
            selected = true
        )

        TableCell(text = device.type, weight = typeWeight, selected = true)
        TableCell(text = device.status.title, weight = statusWeight)
        TableCell(text = device.mac, weight = macWeight)

        TableCell(
            text = device.subscriptions,
            weight = subscriptionsWeight,
            alignment = TextAlign.End
        )
    }
}

@Composable
private fun DeviceListItemTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableCell(
            text = stringResource(id = R.string.title_name),
            weight = nameWeight,
            title = true,
            alignment = TextAlign.Start
        )
        TableCell(
            text = stringResource(id = R.string.title_type),
            weight = typeWeight,
            title = true
        )
        TableCell(
            text = stringResource(id = R.string.title_status),
            weight = statusWeight,
            title = true
        )
        TableCell(
            text = stringResource(id = R.string.title_mac),
            weight = macWeight,
            title = true
        )
        TableCell(
            text = stringResource(id = R.string.title_subscriptions),
            weight = subscriptionsWeight,
            title = true,
            alignment = TextAlign.End
        )
    }
}

@Composable
private fun RowScope.TableCell(
    text: String,
    weight: Float,
    title: Boolean = false,
    alignment: TextAlign = TextAlign.Center,
    selected: Boolean = false
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
        color = if(selected) MaterialTheme.colorScheme.tertiary else Color.Unspecified
    )
}