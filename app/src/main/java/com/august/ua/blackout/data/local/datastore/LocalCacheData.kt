package com.august.ua.blackout.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCacheData @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val DATA = "LocalCacheData"
        private const val USER_DATA = "user_data"
        val userData = intPreferencesKey(USER_DATA)
    }

//        fun notificationsCount(): Flow<Int> {
//            return dataStore.data.catch {
//                emit(emptyPreferences())
//            }.map { preference ->
//                preference[notificationsCount] ?: 0
//            }
//        }

    suspend fun setNotificationsCount(count: Int) {
        dataStore.edit { preference ->
            preference[userData] = count
        }
    }

    suspend fun decreaseNotificationsCount() {
        dataStore.edit { preference ->
            val count = preference[userData]?.minus(1)
            preference[userData] = count?.let { if (it < 0) 0 else count } ?: 0
        }
    }

    suspend fun clearData() {
        dataStore.edit {
            it.clear()
        }
    }
}