package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto

@Entity(tableName = "user_table")
data class UserDbo(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @ColumnInfo("isAllPushTurnOn")
    var isAllPushTurnOn: Boolean = false,
    @ColumnInfo("isOutrageUpdatePushOn")
    var isOutrageUpdatePushOn: Boolean = false,
    @ColumnInfo("isNextDayOutragePushOn")
    var isNextDayOutragePushOn: Boolean = false,
    @ColumnInfo("isGrantPushPermissionScreenSeen")
    var isGrantPushPermissionScreenSeen: Boolean = false,
)

fun UserDbo.toUserDto() = UserDto(
    id = id,
)
