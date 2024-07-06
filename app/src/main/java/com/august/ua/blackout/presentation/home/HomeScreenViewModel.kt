package com.august.ua.blackout.presentation.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.data.dto.Dummy
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
        viewModelScope.launch {
            blackoutRepository.saveOutrages(Dummy.dummyOutragesResponseDto)
        }
    }
}