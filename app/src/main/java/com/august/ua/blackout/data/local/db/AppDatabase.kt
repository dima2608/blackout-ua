package com.august.ua.blackout.data.local.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.august.ua.blackout.data.local.db.dao.AvailableLocationDao
import com.august.ua.blackout.data.local.db.dao.UserDao
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationDbo
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationsWithQueuesDbo
import com.august.ua.blackout.data.local.db.dbo.QueueDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo

@Database(
    entities = [
        UserDbo::class,
        AvailableLocationDbo::class,
        QueueDbo::class,
        //AvailableLocationsWithQueuesDbo::class
    ],
    version = 1,
    autoMigrations = [
        //AutoMigration(from = 1, to = 2)
    ]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun availableLocationsDao(): AvailableLocationDao
}