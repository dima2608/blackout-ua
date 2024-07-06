package com.august.ua.blackout.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.august.ua.blackout.App
import com.august.ua.blackout.R
import com.august.ua.blackout.data.local.db.dao.AvailableLocationDao
import com.august.ua.blackout.data.local.db.dao.CityDao
import com.august.ua.blackout.data.local.db.dao.OutrageDao
import com.august.ua.blackout.data.local.db.dao.UserDao
import com.august.ua.blackout.data.local.db.dao.UserLocationDao
import com.august.ua.blackout.data.local.db.dbo.AvailableLocationDbo
import com.august.ua.blackout.data.local.db.dbo.ColorDbo
import com.august.ua.blackout.data.local.db.dbo.IconDbo
import com.august.ua.blackout.data.local.db.dbo.QueueDbo
import com.august.ua.blackout.data.local.db.dbo.UserDbo
import com.august.ua.blackout.data.local.db.dbo.UserLocationDbo
import com.august.ua.blackout.data.local.db.dbo.converters.Converters
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageQueueDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageShiftDbo
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

@Database(
    entities = [
        UserDbo::class,
        AvailableLocationDbo::class,
        QueueDbo::class,
        ColorDbo::class,
        IconDbo::class,
        UserLocationDbo::class,
        OutrageDbo::class,
        OutrageFullDbo::class,
        OutrageQueueDbo::class,
        OutrageShiftDbo::class,
        CityDbo::class
    ],
    version = 1,
    autoMigrations = [
        //AutoMigration(from = 1, to = 2)
    ]
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun availableLocationsDao(): AvailableLocationDao
    abstract fun userLocationDao(): UserLocationDao
    abstract fun outrageDao(): OutrageDao
    abstract fun cityDao(): CityDao

}