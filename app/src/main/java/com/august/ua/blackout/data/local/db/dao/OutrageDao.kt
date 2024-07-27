package com.august.ua.blackout.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo

@Dao
interface OutrageDao {
//    @Query("SELECT * from outrage_full_table")
//    fun getDevelopers(): LiveData<List<Developer>>

//    @Query("SELECT * from outrage_full_table")
//    suspend fun getDevelopers(): List<OutrageFullDbo>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(outrage: OutrageFullDbo)
//
//    @Query("DELETE FROM outrage_full_table")
//    fun deleteAll()
}