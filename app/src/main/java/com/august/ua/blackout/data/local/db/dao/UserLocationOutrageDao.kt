package com.august.ua.blackout.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import kotlinx.coroutines.flow.Flow

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

    @Query("SELECT * FROM user_location_outrage_table")
    fun observeLocations(): Flow<List<UserLocationOutrageDbo>?>

    @Query("SELECT * FROM user_location_outrage_table ORDER BY location_order ASC")
    fun getLocationsOutragePaging(): PagingSource<Int, UserLocationOutrageDbo>
}