package com.august.ua.blackout.presentation.onboarding

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.MainActivity
import com.august.ua.blackout.MainActivityViewModel
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.dvo.OblastDvo
import com.august.ua.blackout.data.dvo.OblastsDvo
import com.august.ua.blackout.domain.ResultState
import com.august.ua.blackout.domain.repository.UserRepository
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.onboarding.OnboardingPage.*
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent.*
import com.august.ua.blackout.presentation.onboarding.form.UserForm
import com.august.ua.blackout.presentation.onboarding.state.OnboardingBottomSheetState
import com.august.ua.blackout.presentation.onboarding.state.OnboardingScreenState
import com.august.ua.blackout.ui.common.extensions.getDeviceHardwareId
import com.august.ua.blackout.ui.common.extensions.isConnected
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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

    private val _bottomSheetState =
        MutableStateFlow<OnboardingBottomSheetState>(OnboardingBottomSheetState.Initial)
    val bottomSheetState = _bottomSheetState.asStateFlow()

    private var selectedOblast: OblastDvo? = null

    init {
        initUserForm()
        initFcm()
    }

    private fun initUserForm() {
        viewModelScope.launch {
            userRepository.userData.first()?.let {
                userForm.oblastType = it.oblastType
                userForm.queue = it.queue
            }
            initDefaultUiState()
        }
    }

    private fun initDefaultUiState() {
        currentPage = when {
            userForm.isOblastAndQueueSelected() && !userForm.isNotificationPermissionScreenSeen -> GiveNotificationPermission
            userForm.isOblastAndQueueSelected()
                .not() && userForm.onboardingWasStartedBefore -> SelectOblastAndQueue

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
                    oblastType = userForm.oblastType ?: OblastType.Unknown,
                    queue = userForm.queue ?: "",
                    oblastStr = userForm.oblastType.getOblastName(),
                    oblastError = userForm.oblastTypeError,
                    queueError = userForm.queueError
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
            SelectOblast -> selectOblast()
            SelectQueue -> selectQueue()
            ResetScreenState -> resetScreenState()
            OnSnackbarDismissed -> resetScreenState()
            OnSnackbarRetry -> retry()
            is OblastChanged -> oblastChanged(event.oblast)
            is QueueChanged -> queueChanged(event.queue)
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
        _bottomSheetState.update { OnboardingBottomSheetState.Initial }
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
            val user = UserDto(
                id  = null,
                isPushEnabled = null,
                oblastType = userForm.oblastType ?: OblastType.Unknown,
                queue = userForm.queue ?: "-1"
            )

            userRepository.saveNewUserData(user)

        }
    }

    private fun retry() {

    }

    private fun initFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(MainActivity::class.java.simpleName, "token =========>>>>>> $token")
            sendFcmToken(token = token)
        })
    }

    private fun sendFcmToken(token: String) {
        if (getApplication<Application>().isConnected) {
            viewModelScope.launch {
                when (val response = userRepository.sendFcmToken(
                    deviceId = getApplication<Application>().getDeviceHardwareId(),
                    token = token
                )) {
                    is ResultState.Error -> Log.i(
                        MainActivityViewModel::class.java.simpleName,
                        response.errorDvo.toString()
                    )

                    is ResultState.Success -> Log.i(
                        MainActivityViewModel::class.java.simpleName,
                        "FCM token sent successfully"
                    )
                }
            }
        }
    }

    private fun OblastType?.getOblastName() = if (this == OblastType.Unknown || this == null) {
        ""
    } else {
        getApplication<Application>().getString(oblastName)
    }


    private fun selectOblast() {
        _bottomSheetState.update {
            OnboardingBottomSheetState.ShowOblastsBottomSheet(
                oblasts = OblastsDvo(
                    oblasts = listOf(
                        OblastDvo(OblastType.Cherkasy, listOf("1", "2", "3")),
                        OblastDvo(OblastType.Kyiv, listOf("1", "2", "3", "5"))
                    )
                )
            )
        }
    }

    private fun selectQueue() {
        if (userForm.isOblastSelected().not()) {
            userForm.queueError = R.string.select_oblast_first
            configUiState()
            return
        }

        if (userForm.oblastType == null || userForm.oblastType == OblastType.Unknown) {
            showError(getApplication<Application>().getString(R.string.something_went_wrong))
            return
        }

        _bottomSheetState.update {
            OnboardingBottomSheetState.ShowQueueBottomSheet(
                oblast = selectedOblast ?: return
            )
        }
    }

    private fun oblastChanged(oblast: OblastDvo) {
        selectedOblast = oblast
        userForm.oblastType = oblast.oblastType
        userForm.queueError = null
        configUiState()
        resetScreenState()
    }

    private fun queueChanged(queue: String) {
        userForm.queue = queue
        configUiState()
        resetScreenState()
    }

    //whenlogout  clear token
//    userRepository.clearFcmToken(
//    userId = userRepository.userData.firstOrNull()?.id ?: "",
//    deviceId = getApplication<Application>().getDeviceHardwareId()
//    )
}