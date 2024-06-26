package com.august.ua.blackout.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.datastore.LocalCacheData
import com.august.ua.blackout.data.local.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile(LocalCacheData.DATA) })
    }

    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStore<UserDto?> {
        return context.userDataStore
    }
}

val Context.userDataStore: DataStore<UserDto?> by dataStore(
    fileName = "com.blackout.cache",
    serializer = UserPreferencesSerializer
)