package com.august.ua.blackout.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.db.dao.CityDao
import com.august.ua.blackout.data.local.db.dao.OutrageDao
import com.august.ua.blackout.data.local.db.dao.UserDao
import com.august.ua.blackout.data.local.db.dao.UserLocationDao
import com.august.ua.blackout.data.local.db.dao.UserLocationOutrageDao
import com.august.ua.blackout.data.remote.api.BlackoutService
import com.august.ua.blackout.data.remote.api.UserService
import com.august.ua.blackout.data.remote.datasource.BlackoutDataSource
import com.august.ua.blackout.data.remote.datasource.BlackoutDataSourceImpl
import com.august.ua.blackout.data.remote.datasource.UserDataSource
import com.august.ua.blackout.data.remote.datasource.UserDataSourceImpl
import com.august.ua.blackout.data.repository.BlackoutRepositoryImpl
import com.august.ua.blackout.data.repository.UserLocationsRepositoryImpl
import com.august.ua.blackout.data.repository.UserRepositoryImpl
import com.august.ua.blackout.domain.repository.BlackoutRepository
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import com.august.ua.blackout.domain.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideBlackoutService(retrofit: Retrofit): BlackoutService {
        return create(retrofit)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return create(retrofit)
    }


    @Provides
    fun provideBlackoutDataSource(blackoutService: BlackoutService): BlackoutDataSource {
        return BlackoutDataSourceImpl(blackoutService = blackoutService)
    }

    @Provides
    fun provideUserDataSource(userService: UserService): UserDataSource {
        return UserDataSourceImpl(userService = userService)
    }

    @Provides
    @Singleton
    fun provideBlackoutRepository(
        blackoutService: BlackoutService,
        outrageDao: OutrageDao,
        cityDao: CityDao
    ): BlackoutRepository {
        return BlackoutRepositoryImpl(
            blackoutService = blackoutService,
            outrageDao = outrageDao,
            cityDao = cityDao
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataStorePreferences: DataStore<UserDto?>,
        sharedPreferences: SharedPreferences,
        userDataSource: UserDataSource,
        userDao: UserDao
    ): UserRepository<Flow<UserDto?>, UserDto> {
        return UserRepositoryImpl(
            userDataStorePreferences = userDataStorePreferences,
            sharedPreferences = sharedPreferences,
            userDataSource = userDataSource,
            userDao = userDao
        )
    }

    @Provides
    @Singleton
    fun provideUserLocationsRepository(
        userLocationDao: UserLocationDao,
        userLocationOutrageDao: UserLocationOutrageDao,
        @ApplicationContext context: Context,
    ): UserLocationsRepository {
        return UserLocationsRepositoryImpl(
            userLocationDao = userLocationDao,
            userLocationOutrageDao = userLocationOutrageDao,
            context = context

        )
    }

    private inline fun <reified T> create(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }
}