package com.august.ua.blackout.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserDbo?

    @Query("SELECT * FROM user LIMIT 1")
    fun observeUser(): Flow<UserDbo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDbo: UserDbo)

//    @Query("UPDATE user SET current_location = :location WHERE id = :id")
//    suspend fun updatePushEnabled(id: String, location: OblastType)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Transaction
    suspend fun update(userDbo: UserDbo) {
        deleteAll()
        insert(userDbo)
    }
}