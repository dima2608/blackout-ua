package com.august.ua.blackout.presentation.notification_permission

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.domain.repository.user.UserRepository
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.notification_permission.event.NotificationPermissionEvent
import com.august.ua.blackout.presentation.notification_permission.state.NotificationPermissionScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiveNotificationPermissionViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val userRepository: UserRepository<Flow<UserDto?>, UserDto>
) : AndroidViewModel(context as Application) {
    private val _screenState =
        MutableStateFlow<NotificationPermissionScreenState>(NotificationPermissionScreenState.InitialState)
    val screenState: StateFlow<NotificationPermissionScreenState> = _screenState.asStateFlow()

    private val _navigationState =
        MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navigationState: StateFlow<NavigationEvent> = _navigationState.asStateFlow()

    fun onEvent(event: NotificationPermissionEvent) {
        when (event) {
            NotificationPermissionEvent.AskPermissionEvent -> askNotificationPermission()
            is NotificationPermissionEvent.IsPermissionGranted -> processIsPermissionGranted(event.isGranted)
            NotificationPermissionEvent.OnNavigateBackEvent -> processOnNavigateBack()
        }
    }

    private fun askNotificationPermission() {
        _screenState.update { NotificationPermissionScreenState.AskPermissionState }
    }

    private fun processIsPermissionGranted(isGranted: Boolean) {
        viewModelScope.launch {
            setDoNotShowAskNotificationScreenAgain()
            _navigationState.update { NavigationEvent.CloseScreen }
        }
    }

    private suspend fun setDoNotShowAskNotificationScreenAgain() {
        userRepository.saveIsAskNotificationScreenWasShown(true)
        //sharedPreferences.saveIsAskNotificationScreenWasShown(true)
    }

    private fun processOnNavigateBack() {
        viewModelScope.launch {
            setDoNotShowAskNotificationScreenAgain()
            _navigationState.update { NavigationEvent.CloseScreen }
        }
    }

    fun resetUiState() {
        _screenState.update { NotificationPermissionScreenState.InitialState }
    }

    fun resetNavigateBackState() {
        _navigationState.update { NavigationEvent.None }
    }
}