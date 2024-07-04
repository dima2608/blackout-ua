package com.august.ua.blackout.data.local.db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "color")
data class ColorDbo(
    @PrimaryKey(autoGenerate = true)
    val colorId: Long = 0,
    @ColumnInfo("colorSRGB")
    val colorSRGB: Int,
)