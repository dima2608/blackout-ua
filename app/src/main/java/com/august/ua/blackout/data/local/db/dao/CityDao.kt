package com.august.ua.blackout.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cites: List<CityDbo>)

    @Query("DELETE FROM city_table")
    fun deleteAll()
}