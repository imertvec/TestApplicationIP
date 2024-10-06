package ru.vagavagus.design.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    titleLarge = TextStyle(fontFamily = Merriweather, fontWeight = FontWeight.Bold, fontSize = 20.sp),
    bodyMedium = TextStyle(fontFamily = Merriweather, fontWeight = FontWeight.Bold),
    bodySmall = TextStyle(fontFamily = Merriweather, fontWeight = FontWeight.Normal, fontSize = 12.sp)
)