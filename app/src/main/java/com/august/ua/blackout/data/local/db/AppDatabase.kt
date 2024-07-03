package com.august.ua.blackout.data.local.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.august.ua.blackout.data.local.db.dao.UserDao
import com.august.ua.blackout.data.local.db.dbo.UserDbo

@Database(
    entities = [
        UserDbo::class,
        //WarehouseDbo::class,
        //TemplateDbo::class,
        //GalleryImageDbo::class
    ],
    version = 1,
    autoMigrations = [
        //AutoMigration(from = 1, to = 2)
    ]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
//    abstract fun warehousesDao(): WarehousesDao
//    abstract fun templatesDao(): TemplatesDao
//    abstract fun galleryImageDao(): GalleryImageDao
}