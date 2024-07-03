package com.august.ua.blackout.data.local.db.dao

import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface AvailableLocationDao {

    @Query("SELECT * FROM available_location LIMIT 1")
    suspend fun getAvailableLocations(): AvailableLocationDbo?

    @Query("SELECT * FROM available_location LIMIT 1")
    fun observeAvailableLocations(): Flow<List<AvailableLocationDbo>?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(locations: List<AvailableLocationDbo>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Transaction
    suspend fun update(availableLocations: List<AvailableLocationDbo>) {
        deleteAll()
        insert(availableLocations)
    }
}