package com.august.ua.blackout.di

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.remote.datasource.UserDataSource
import com.august.ua.blackout.data.repository.UserRepositoryImpl
import com.august.ua.blackout.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

//    @Provides
//    @Singleton
//    fun provideAuthService(retrofit: Retrofit): AuthService {
//        return create(retrofit)
//    }


//    @Provides
//    fun provideComplexDataSource(
//        complexService: ComplexService,
//        userRepository: UserRepository<Flow<UserDto?>, UserDto>,
//    ): ComplexDataSource {
//        return ComplexDataSourceImpl(complexService = complexService, userRepository)
//    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataStorePreferences: DataStore<UserDto?>,
        sharedPreferences: SharedPreferences,
    ): UserRepository<Flow<UserDto?>, UserDto> {
        return UserRepositoryImpl(
            userDataStorePreferences = userDataStorePreferences,
            sharedPreferences = sharedPreferences
        )
    }

    private inline fun <reified T> create(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }
}