package com.august.ua.blackout.data.repository

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import com.august.ua.blackout.data.dto.Oblast
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDataStorePreferences: DataStore<UserDto?>,
    private val sharedPreferences: SharedPreferences,
): UserRepository<Flow<UserDto?>, UserDto> {

    override val userData: Flow<UserDto?> = userDataStorePreferences.data

    override suspend fun saveNewUserData(user: UserDto) {
        userDataStorePreferences.updateData {
            userDataStorePreferences.updateData {
                user
            }
        }
    }

    override suspend fun saveOblast(oblast: Oblast) {
        userDataStorePreferences.updateData {
            it!!.copy(
                oblast = oblast
            )
        }
    }

    override suspend fun saveQueue(queue: Int) {
        userDataStorePreferences.updateData {
            it!!.copy(
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

}