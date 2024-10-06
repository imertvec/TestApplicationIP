package ru.vagavagus.devices_list.screen.ui_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.vagavagus.design.extensions.spacing
import ru.vagavagus.devices_list.model.Device
import ru.vagavagus.feature.devicesList.R

@Composable
fun DeviceDetails(
    device: Device?,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = device != null
    ) {
        if(device != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.Start
            ) {
                val sep = ":"

                Text(
                    text = device.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(text = stringResource(id = R.string.title_type) + "$sep ${device.type}")
                Text(text = stringResource(id = R.string.title_status) + "$sep ${device.status.title}")
                Text(text = stringResource(id = R.string.title_mac) + "$sep ${device.mac}")
                Text(text = stringResource(id = R.string.title_subscriptions) + "$sep ${device.subscriptions}")
            }
        }
    }
}