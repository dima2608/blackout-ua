package com.august.ua.blackout.data.dto

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.august.ua.blackout.R

enum class Oblast(
    id: String,
    @StringRes
    name: Int,
    isBlackoutsKnown: Boolean
) {
    Cherkasy(
        id = "Che",
        name = R.string.cherkasy,
        isBlackoutsKnown = true
    )
}