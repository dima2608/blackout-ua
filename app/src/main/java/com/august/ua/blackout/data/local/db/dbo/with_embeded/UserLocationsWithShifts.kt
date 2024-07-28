package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.Embedded
import androidx.room.Relation
import com.august.ua.blackout.data.local.db.dbo.UserDbo

data class UserLocationsWithShifts(
    @Embedded
    val location: UserLocationOutrageDbo,
    @Relation(
        parentColumn = "locationId",
        entityColumn = "parent_location_id"
    )
    val shifts: List<UserLocationShiftDbo>
)
