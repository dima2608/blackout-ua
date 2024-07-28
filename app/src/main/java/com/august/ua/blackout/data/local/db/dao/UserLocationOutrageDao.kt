package com.august.ua.blackout.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.august.ua.blackout.data.local.db.dbo.ShiftDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationShiftDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationsWithShifts
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserWithAllLocations
import kotlinx.coroutines.flow.Flow

@Dao
interface UserLocationOutrageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: UserLocationOutrageDbo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSifts(shifts: List<UserLocationShiftDbo>)

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

    @Query("DELETE FROM user_location_shift_table WHERE shift_date = :date")
    suspend fun deleteAllShiftWithDate(date: String)

    @Transaction
    suspend fun updateShifts(
        shifts: List<UserLocationShiftDbo>,
        date: String
    ) {
        deleteAllShiftWithDate(date)
        insertSifts(shifts)
    }

    @Transaction
    @Query("SELECT * FROM user_location_outrage_table WHERE locationId IN (" +
            "SELECT parent_location_id FROM user_location_shift_table WHERE shift_date = :date" +
            ") ORDER BY location_order ASC")
    fun getUserWithLocationsWithShiftsPaging(date: String): PagingSource<Int, UserLocationsWithShifts>
}