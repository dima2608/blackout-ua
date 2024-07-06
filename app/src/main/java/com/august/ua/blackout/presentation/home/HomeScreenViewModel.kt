package com.august.ua.blackout.presentation.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.data.dto.Dummy
import com.august.ua.blackout.data.dto.OblastResponseDto
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.BlackoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val blackoutRepository: BlackoutRepository
): AndroidViewModel(context as Application) {

    init {
        getAvailableCities()
    }
    private fun getAvailableCities() {
        viewModelScope.launch {
//            val response = blackoutRepository.getOblast()
//
//            when(response) {
//                is ResultState.Error -> TODO()
//                is ResultState.Success -> saveAvailableCities(response as OblastResponseDto?)
//            }
            saveAvailableCities(Dummy.dummyOblastResponseDtoDto)
        }

    }

    private fun getSavedLocationQueues() {

    }

    private suspend fun saveAvailableCities(cities: OblastResponseDto?) {
        if (cities == null || cities.oblasts.isNullOrEmpty()) return

        blackoutRepository.saveCities(cities)

    }
}