package com.august.ua.blackout.di

import android.content.Context
import androidx.room.Room
import com.august.ua.blackout.data.local.db.AppDatabase
import com.august.ua.blackout.data.local.db.AppDatabaseCallBack
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    const val DATA_BASE_NAME = "blackout_cache"

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATA_BASE_NAME
        )
            //.addMigrations(MIGRATION_3_4)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()
    @Provides
    fun provideAvailableLocationDao(appDatabase: AppDatabase) = appDatabase.availableLocationsDao()
    @Provides
    fun provideOutrageDao(appDatabase: AppDatabase) = appDatabase.outrageDao()
    @Provides
    fun provideCityDao(appDatabase: AppDatabase) = appDatabase.cityDao()

    @Provides
    fun appDatabaseCallback(
        appDatabase: AppDatabase,
        @ApplicationContext context: Context,
        @ApplicationScope  externalScope: CoroutineScope
    ) = AppDatabaseCallBack(
        appDatabase = appDatabase,
        context = context,
        scope = externalScope
    )
}