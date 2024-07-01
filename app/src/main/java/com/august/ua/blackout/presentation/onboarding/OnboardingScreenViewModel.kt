package com.august.ua.blackout.presentation.onboarding

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.Oblast
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.domain.common.EMPTY_STRING
import com.august.ua.blackout.domain.repository.UserRepository
import com.august.ua.blackout.navigation.Screen
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.onboarding.OnboardingPage.*
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent.*
import com.august.ua.blackout.presentation.onboarding.form.UserForm
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val userRepository: UserRepository<Flow<UserDto?>, UserDto>,
) : AndroidViewModel(context as Application) {

    private var currentPage = Undefined
    private val userForm = UserForm()

    private val _uiState = MutableStateFlow<OnboardingScreenState>(OnboardingScreenState.HelloState)
    val uiState: StateFlow<OnboardingScreenState> = _uiState.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None)
    val screenState = _screenState.asStateFlow()

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()


    init {
        initUserForm()
    }

    private fun initUserForm() {
        viewModelScope.launch {

            userRepository.userData.first()?.let {
                userForm.oblast = it.oblast
                userForm.queue = it.queue
            }
            initDefaultUiState()
        }
    }

    private fun initDefaultUiState() {
        currentPage = when {
            userForm.isOblastAndQueueSelected() && !userForm.isNotificationPermissionScreenSeen -> GiveNotificationPermission
            userForm.isOblastAndQueueSelected().not() && userForm.onboardingWasStartedBefore -> SelectOblastAndQueue
            else -> Hello
        }
        configUiState()
    }

    private fun configUiState() {
        _uiState.update {
            when (currentPage) {
                Undefined -> OnboardingScreenState.Initial
                Hello -> OnboardingScreenState.HelloState
                SelectOblastAndQueue -> OnboardingScreenState.SelectOblastAndQueueState(
                    oblast = userForm.oblast ?: Oblast.Unknown,
                    queue = userForm.queue ?: "-1"
                )

                GiveNotificationPermission -> OnboardingScreenState.GivePermissionState
                Enjoy -> OnboardingScreenState.CompleteState
            }
        }
    }

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            FinishOnboarding -> finishOnboarding()
            GivePermission -> givePermission()
            NextScreen -> processOnNextEvent()
            PreviousScreen -> onBackPressed()
            SelectOblast -> TODO()
            SelectQueue -> TODO()
            ResetScreenState -> resetScreenState()
            OnSnackbarDismissed -> resetScreenState()
            OnSnackbarRetry -> retry()
        }
    }

    private fun onBackPressed() {
        if (_screenState.replayCache.firstOrNull() == ScreenState.Loading) {
            return
        }
        resetScreenState()
        when (currentPage) {
            Undefined -> Unit
            Hello -> _navEvent.update { NavigationEvent.CloseScreen }
            SelectOblastAndQueue -> {
                currentPage = Hello
                configUiState()
            }

            GiveNotificationPermission -> {
                currentPage = SelectOblastAndQueue
                configUiState()
            }

            Enjoy -> {
                currentPage = GiveNotificationPermission
                configUiState()
            }
        }
    }

    private fun finishOnboarding() {

    }

    private fun givePermission() {

    }

    private fun resetScreenState() {
        _screenState.update { ScreenState.None }
    }

    private fun processOnNextEvent() {
        resetScreenState()
        viewModelScope.launch {
            when (currentPage) {
                Undefined -> Unit
                Hello -> {
                    currentPage = SelectOblastAndQueue
                    configUiState()
                }

                SelectOblastAndQueue -> {
                    if (userForm.isOblastAndQueueSelected()) {
                        currentPage = GiveNotificationPermission
                        saveUserOblastAndQueue()
                        configUiState()
                    } else showError(processOblastAndQueueErrors())
                }

                GiveNotificationPermission -> {
                    currentPage = Enjoy
                    configUiState()
                }

                Enjoy -> {

                }
            }
        }
    }

    private fun processOblastAndQueueErrors() = when {
        userForm.isOblastAndQueueSelected()
            .not() -> getApplication<Application>().getString(R.string.select_oblast_and_queue_error)

        userForm.isOblastSelected()
            .not() -> getApplication<Application>().getString(R.string.select_oblast_error)

        userForm.isQueueSelected()
            .not() -> getApplication<Application>().getString(R.string.select_queue_error)

        else -> getApplication<Application>().getString(R.string.something_went_wrong)
    }


    private fun showError(error: String) {
        _screenState.update { ScreenState.ErrorState(error) }
    }

    private fun saveUserOblastAndQueue() {
        viewModelScope.launch {
            userRepository.saveOblast(userForm.oblast ?: Oblast.Unknown)
            userRepository.saveQueue(userForm.queue ?: "-1")
        }
    }

    private fun retry() {

    }
}