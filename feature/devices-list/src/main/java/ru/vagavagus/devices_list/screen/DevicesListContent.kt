package ru.vagavagus.devices_list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.devices_list.screen.components.DevicesListEvent
import ru.vagavagus.devices_list.screen.components.DevicesListState
import ru.vagavagus.devices_list.screen.ui_components.DeviceDetails
import ru.vagavagus.devices_list.screen.ui_components.DevicesListBlock
import ru.vagavagus.devices_list.screen.ui_components.StatusBar

@Composable
internal fun DevicesListContent(
    uiState: DevicesListState,
    handleEvent: (DevicesListEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)

    ) {
        StatusBar(
            selectedStatus = uiState.selectedStatus,
            onStatusClick = { status -> handleEvent(DevicesListEvent.ChangeSelectedStatus(status)) }
        )

        HorizontalDivider()

        DevicesListBlock(
            devicesState = uiState.devices,
            onDeviceClick = { device -> handleEvent(DevicesListEvent.DeviceClick(device)) },
            onRetryFetchDevicesClick = { handleEvent(DevicesListEvent.RetryFetchDevicesClick) }
        )

        DeviceDetails(
            modifier = Modifier.fillMaxWidth(),
            device = uiState.selectedDevice
        )
    }
}