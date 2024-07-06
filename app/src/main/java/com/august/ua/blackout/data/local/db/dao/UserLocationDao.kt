package com.august.ua.blackout.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserLocationDao {
    @Query("SELECT * FROM user_location")
    suspend fun getLocations(): List<UserLocationDbo?>

    @Query("SELECT * FROM user_location")
    fun observeUserLocations(): Flow<List<UserLocationDbo?>>

    @Query("SELECT selected_queue FROM user_location")
    suspend fun getQueues(): List<String?>

    @Query("SELECT * FROM user_location ORDER BY location_order ASC")
    fun getLocationsQueuePaging(): PagingSource<Int, UserLocationDbo>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userLocationDbo: UserLocationDbo)

    @Query("DELETE FROM user_location WHERE locationId = :locationId ")
    suspend fun deleteUserLocationById(locationId: Long)
}