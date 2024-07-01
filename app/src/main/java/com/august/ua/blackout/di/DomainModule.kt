package com.august.ua.blackout.di

import com.august.ua.blackout.domain.repository.BlackoutRepository
import com.august.ua.blackout.domain.use_kase.GetOblastsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetOblastsUseCase(blackoutRepository: BlackoutRepository) =
        GetOblastsUseCase(blackoutRepository = blackoutRepository)
}