package ru.vagavagus.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import ru.vagavagus.core.navigation.R

@Serializable
sealed class BottomTabs(
    @StringRes private val titleId: Int,
) {
    val title: String
        @Composable get() = stringResource(titleId)

    @Serializable
    object DevicesList: BottomTabs(R.string.bottom_bar_device_list)

    @Serializable
    object Messages: BottomTabs(R.string.bottom_bar_messages)

    companion object {
        val entries = BottomTabs::class.sealedSubclasses.map {
            it.objectInstance as BottomTabs
        }
    }
}