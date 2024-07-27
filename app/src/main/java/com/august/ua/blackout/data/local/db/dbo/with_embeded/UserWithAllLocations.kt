package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.Embedded
import androidx.room.Relation
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.db.dbo.BlackoutDbo
import com.august.ua.blackout.data.local.db.dbo.ShiftDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo

data class UserWithAllLocations(
    @Embedded
    val user: UserDbo,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val locations: List<UserLocationOutrageDbo>
)