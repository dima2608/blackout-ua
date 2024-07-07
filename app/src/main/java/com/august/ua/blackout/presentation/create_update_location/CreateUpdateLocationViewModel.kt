package com.august.ua.blackout.presentation.create_update_location

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.R
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.create_update_location.CreateUpdateLocationPage.*
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent
import com.august.ua.blackout.presentation.create_update_location.event.CreateUpdateLocationEvent.*
import com.august.ua.blackout.presentation.create_update_location.form.CreateUpdateLocationForm
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState.*
import com.august.ua.blackout.presentation.onboarding.OnboardingPage
import com.august.ua.blackout.presentation.onboarding.OnboardingPage.Enjoy
import com.august.ua.blackout.presentation.onboarding.OnboardingPage.GiveNotificationPermission
import com.august.ua.blackout.presentation.onboarding.OnboardingPage.Hello
import com.august.ua.blackout.presentation.onboarding.OnboardingPage.SelectOblastAndQueue
import com.august.ua.blackout.presentation.onboarding.form.UserForm
import com.august.ua.blackout.presentation.onboarding.state.OnboardingBottomSheetState
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUpdateLocationViewModel @Inject constructor(
    @ApplicationContext context: Context,
): AndroidViewModel(context as Application) {

    private var currentPage = Undefined
    private val form = CreateUpdateLocationForm()

    private val _uiState = MutableStateFlow<CreateUpdateLocationState>(Initial)
    val uiState: StateFlow<CreateUpdateLocationState> = _uiState.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None)
    val screenState = _screenState.asStateFlow()

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()

    init {
        initForm()
    }

    private fun initForm() {
        viewModelScope.launch {
//            userRepository.userData.first()?.let {
//                userForm.oblastType = it.oblastType
//                userForm.queue = it.queue
//            }
            initDefaultUiState()
        }
    }

    private fun initDefaultUiState() {
        currentPage = Location
        configUiState()
    }

    private fun configUiState() {
        _uiState.update {
            when (currentPage) {
                Undefined -> Initial
                Location -> LocationState(
                    listCities = emptyList(),
                    toolbar = R.string.create_location
                )
                Queue -> LocationQueueState(
                    link = "",
                    toolbar = R.string.create_location,
                    selectedCity = form.selectedCity ?: return

                )
                LocationSettings -> LocationSettingsState(
                    toolbar = R.string.create_location,
                    locationName = form.name ?: "",
                    selectedColorType = form.selectedColorType,
                    selectedIconType = form.selectedIconType
                )
                Push -> LocationPushState(
                    toolbar = R.string.create_location,
                    isOn = form.isPushOn
                )
            }
        }
    }

    fun onUiEvent(event: CreateUpdateLocationEvent) {
        when(event) {
            is CityChanged -> TODO()
            is ColorChanged -> TODO()
            is IconChanged -> TODO()
            is NameChanged -> TODO()
            NextScreen -> processOnNextEvent()
            is OnPushOn -> TODO()
            PreviousScreen -> onBackPressed()
            is QueueChanged -> TODO()
        }
    }

    private fun resetScreenState() {
        _screenState.update { ScreenState.None }
    }

    private fun showError(error: String) {
        _screenState.update { ScreenState.ErrorState(error) }
    }

    private fun processOnNextEvent() {
        resetScreenState()
        viewModelScope.launch {
            when (currentPage) {
                Undefined -> Unit
                Location -> {
                    //toDo add validation
                    currentPage = Queue
                    configUiState()
                }
                Queue -> {
                    currentPage = LocationSettings
                    configUiState()
                }
                LocationSettings -> {
                    currentPage = LocationSettings
                    configUiState()
                }
                Push -> {

                }
            }
        }
    }

    private fun onBackPressed() {
        if (_screenState.replayCache.firstOrNull() == ScreenState.Loading) {
            return
        }
        resetScreenState()
        when (currentPage) {
            Undefined -> Unit
            Location -> _navEvent.update { NavigationEvent.CloseScreen }
            Queue -> {
                currentPage = Location
                configUiState()
            }
            LocationSettings -> {
                currentPage = Queue
                configUiState()
            }
            Push -> {
                currentPage = LocationSettings
                configUiState()
            }
        }
    }
}