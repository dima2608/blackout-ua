package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class OblastResponseDto(
    val oblasts: List<OblastDto>?
): Entity, Parcelable