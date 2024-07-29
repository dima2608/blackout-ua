package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.domain.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class OutrageSearchDto(
    val regions: List<OutrageRegionsDto>?
): Entity, Parcelable

