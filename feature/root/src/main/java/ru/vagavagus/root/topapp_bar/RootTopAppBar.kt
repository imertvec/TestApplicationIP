package ru.vagavagus.root.topapp_bar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RootTopAppBar(title: String) {
    CenterAlignedTopAppBar(title = { Text(text = title) })
}