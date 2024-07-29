package com.august.ua.blackout.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationsWithQueuesDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserWithAllLocations
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUser(): UserDbo?

    @Query("SELECT * FROM user_table LIMIT 1")
    fun observeUser(): Flow<UserDbo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDbo: UserDbo)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("UPDATE user_table SET isGrantPushPermissionScreenSeen = :wasSeen")
    suspend fun setIsNotificationPermissionScreenWasSeen(wasSeen:Boolean)

    @Transaction
    suspend fun update(userDbo: UserDbo) {
        deleteAll()
        insert(userDbo)
    }

    @Transaction
    @Query("SELECT * FROM user_table")
    suspend fun getUserWithLocations(): UserWithAllLocations?
}