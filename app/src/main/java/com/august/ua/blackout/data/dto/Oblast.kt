package com.august.ua.blackout.data.dto

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.august.ua.blackout.R

enum class Oblast(
    id: String,
    @StringRes
    name: Int,
) {
    Cherkasy(
        id = "Che",
        name = R.string.cherkasy
    ),

    Unknown(
        id = "-1",
        name = R.string.unknown,
    )
}