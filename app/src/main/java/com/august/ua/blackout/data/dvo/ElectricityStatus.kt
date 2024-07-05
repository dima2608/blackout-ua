package com.august.ua.blackout.data.dvo

import androidx.compose.ui.graphics.Color
import com.august.ua.blackout.ui.theme.Green
import com.august.ua.blackout.ui.theme.Red

enum class ElectricityStatus(
    val color: Color
) {
    Available(
        color = Green
    ),
    Unavailable(
        color = Red
    )
}