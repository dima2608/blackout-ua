package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "icon")
data class IconDbo(
    @PrimaryKey(autoGenerate = true)
    val iconId: Long = 0,
    @ColumnInfo("icon")
    val icon: Int,
)