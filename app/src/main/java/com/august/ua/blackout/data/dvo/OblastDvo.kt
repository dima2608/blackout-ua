package com.august.ua.blackout.data.dvo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OblastDvo(
    val id: String,
    @DrawableRes
    val icon: Int,
    @StringRes
    val name: Int,
)