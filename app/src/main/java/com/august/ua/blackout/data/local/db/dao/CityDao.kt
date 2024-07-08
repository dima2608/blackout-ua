package com.august.ua.blackout.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cites: List<CityDbo>)

    @Query("SELECT * FROM city_table")
    suspend fun getCities(): List<CityDbo>

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()

    @Transaction
    suspend fun update(cites: List<CityDbo>) {
        deleteAll()
        insert(cites)
    }
}