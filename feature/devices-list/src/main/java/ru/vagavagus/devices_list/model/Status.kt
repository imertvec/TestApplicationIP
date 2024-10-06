package ru.vagavagus.devices_list.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.vagavagus.feature.devicesList.R

enum class Status(
    @StringRes private val titleId: Int
) {
    Live(R.string.status_live),
    Approved(R.string.status_approved),
    Mute(R.string.status_mute),
    Blocked(R.string.status_blocked),
    Dead(R.string.status_dead);

    val title: String
        @Composable get() = stringResource(id = titleId)
}