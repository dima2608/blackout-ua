package com.august.ua.blackout.data.local.db.dbo

import androidx.room.Embedded
import androidx.room.Relation

data class BlackoutWithShiftsDbo(
    @Embedded
    val blackout: BlackoutDbo,
    @Relation(
        parentColumn = "blackoutId",
        entityColumn = "parentBlackoutId"
    )
    val shifts: List<ShiftsDbo>

)