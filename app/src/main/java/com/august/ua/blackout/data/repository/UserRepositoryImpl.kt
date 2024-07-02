package com.august.ua.blackout.data.repository

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.datastore.LocalCacheData
import com.august.ua.blackout.data.remote.datasource.UserDataSource
import com.august.ua.blackout.data.remote.network.toResultState
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDataStorePreferences: DataStore<UserDto?>,
    private val localCacheData: LocalCacheData,
    private val sharedPreferences: SharedPreferences,
    private val userDataSource: UserDataSource
): UserRepository<Flow<UserDto?>, UserDto> {

    override val userData: Flow<UserDto?> = userDataStorePreferences.data

    override suspend fun saveNewUserData(user: UserDto) {
        userDataStorePreferences.updateData {
            userDataStorePreferences.updateData {
                user
            }
        }
    }

    override suspend fun saveOblast(oblastType: OblastType) {
        userDataStorePreferences.updateData {
            it?.copy(
                oblastType = oblastType
            )
        }
    }

    override suspend fun saveQueue(queue: String) {
        userDataStorePreferences.updateData {
            it?.copy(
                queue = queue
            )
        }
    }

    override suspend fun clearUserLocalData() {
        clearUserData()
        //sharedPreferences.clear()
    }

    override suspend fun clearUserData() {
        userDataStorePreferences.updateData {
            null
        }
    }

    override suspend fun sendFcmToken(deviceId: String, token: String): ResultState<Unit> {
        return userDataSource.sendFcmToken(deviceId = deviceId, token = token).toResultState { it }
    }

    override suspend fun clearFcmToken(
        deviceId: String,
        userId: String
    ): ResultState<Unit> {
        return userDataSource.clearFcmToken(
            deviceId = deviceId,
            userId = userId
        ).toResultState { it }
    }

}