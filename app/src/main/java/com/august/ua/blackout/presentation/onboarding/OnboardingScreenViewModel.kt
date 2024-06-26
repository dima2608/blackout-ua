package com.august.ua.blackout.presentation.onboarding

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    @ApplicationContext context: Context
) : AndroidViewModel(context as Application) {

    private val _uiState = MutableStateFlow<OnboardingScreenState>(OnboardingScreenState.HelloState)
    val uiState: StateFlow<OnboardingScreenState> = _uiState.asStateFlow()


}