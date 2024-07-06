package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.august.ua.blackout.data.dto.OblastType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city_table")
data class CityDbo(
    @PrimaryKey(autoGenerate = true)
    val cityId: Long = 0,
    @Expose
    val id: Int?,
    @Expose
    val oblastType: OblastType?,
    @Expose
    val queues: List<String>?
)