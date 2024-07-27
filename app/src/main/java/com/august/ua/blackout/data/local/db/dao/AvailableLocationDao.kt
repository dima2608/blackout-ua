package com.august.ua.blackout.data.local.db.dao

import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.august.ua.blackout.data.dto.OblastDto
import com.august.ua.blackout.data.dto.toAvailableLocationDbo
import com.august.ua.blackout.data.dto.toQueueDboList
import com.august.ua.blackout.data.dvo.OblastsDvo
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationDbo
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationsWithQueuesDbo
import com.august.ua.blackout.data.local.db.dbo.QueueDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface AvailableLocationDao {

//    @Query("SELECT * FROM available_location")
//    suspend fun getAvailableLocations(): AvailableLocationDbo?
//
//    @Query("SELECT * FROM available_location")
//    fun observeAvailableLocations(): Flow<List<AvailableLocationDbo>?>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAllAvailableLocations(locations: List<AvailableLocationDbo>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAvailableLocation(locations: AvailableLocationDbo): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAllQueues(locations: List<QueueDbo>)
//
//    @Query("DELETE FROM available_location")
//    suspend fun deleteAll()
//
//    @Transaction
//    suspend fun update(locations: List<AvailableLocationDbo>) {
//        deleteAll()
//        insertAllAvailableLocations(locations)
//    }
//
//    @Transaction
//    @Query("SELECT * FROM available_location")
//    suspend fun getAvailableLocationsWithQueue(): List<AvailableLocationsWithQueuesDbo>
//
//    @Transaction
//    suspend fun insetAllAvailableLocations(locations: List<OblastDto>) {
//        for (location in locations) {
//            Log.e("TEST", "location: $location")
//            val id = insertAvailableLocation(location.toAvailableLocationDbo())
//            insertAllQueues(location.toQueueDboList(id))
//        }
//    }
}