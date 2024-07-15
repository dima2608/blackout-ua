package com.august.ua.blackout.data.dvo

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.august.ua.blackout.R
import com.august.ua.blackout.ui.theme.Green
import com.august.ua.blackout.ui.theme.Red

enum class ElectricityStatus(
    val color: Color,
    @StringRes
    val statusName: Int,
    @StringRes
    val descriptionPeriodName: Int
) {
    Available(
        color = Green,
        statusName = R.string.app_name,
        descriptionPeriodName = R.string.turn_off_in

    ),
    Unavailable(
        color = Red,
        statusName = R.string.light_unavailable,
        descriptionPeriodName = R.string.turn_on_in
    )
}