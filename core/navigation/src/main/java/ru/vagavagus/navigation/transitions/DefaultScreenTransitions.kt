package ru.vagavagus.navigation.transitions

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally


val ScreenEnterTransition = slideInHorizontally { it }
val ScreenExitTransition = slideOutHorizontally { it }