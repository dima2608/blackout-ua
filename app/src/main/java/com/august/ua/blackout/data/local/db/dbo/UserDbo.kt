package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto

@Entity(tableName = "user")
data class UserDbo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("current_location")
    val location: OblastType?

)

fun UserDbo.toUserDto() = UserDto(
    id = id.toString(),
    isPushEnabled = false,
    oblastType = location,
    queue = "1"
)
