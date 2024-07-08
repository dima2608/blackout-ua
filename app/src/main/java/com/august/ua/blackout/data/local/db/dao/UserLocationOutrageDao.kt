package com.august.ua.blackout.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo

@Dao
interface UserLocationOutrageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: UserLocationOutrageDbo): Long


    @Query("DELETE FROM user_location_outrage_table")
    fun deleteAll()

    @Query("DELETE FROM user_location_outrage_table WHERE locationId = :locationId ")
    suspend fun deleteLocationById(locationId: Long)

    @Query("SELECT COUNT(*) FROM user_location_outrage_table")
    suspend fun getLocationsSize(): Int
}