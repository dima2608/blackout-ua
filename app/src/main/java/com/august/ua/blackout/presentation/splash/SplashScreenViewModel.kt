package com.august.ua.blackout.presentation.splash

import android.app.Application
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastDto
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.dto.toAvailableLocationDbo
import com.august.ua.blackout.data.dto.toQueueDboList
import com.august.ua.blackout.data.local.db.dao.AvailableLocationDao
import com.august.ua.blackout.domain.repository.UserRepository
import com.august.ua.blackout.navigation.NavigationPath
import com.august.ua.blackout.navigation.Screen
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.splash.model.SplashJob
import com.august.ua.blackout.ui.infrastructure.event.UIAction
import com.august.ua.blackout.ui.utils.resource.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val userRepository: UserRepository<Flow<UserDto?>, UserDto>,
    private val availableLocationDao: AvailableLocationDao
) : AndroidViewModel(context as Application) {

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()

    val uiData = mutableStateOf(
        SplashScreenData(
            UiText.StringResource(R.string.splash_screen_title_text),
            ANIMATION_END_KEY
        )
    )

    init {
        //checkIsUserDataPresent()
        getAllAvailableLocations()
    }


    private fun getAllAvailableLocations() {
        viewModelScope.launch {
            val availableLocations = listOf(
                OblastDto(
                    oblastType = OblastType.Kyiv,
                    queues = listOf("1", "2", "3")
                ),
                OblastDto(
                    oblastType = OblastType.Cherkasy,
                    queues = listOf("5", "3", "2", "1")
                ),
                OblastDto(
                    oblastType = OblastType.Sumy,
                    queues = listOf("5.1", "3.1", "2.3", "1.4")
                ),
                OblastDto(
                    oblastType = OblastType.Mykolaiv,
                    queues = listOf("5.1", "3.1", "2.3", "1.4")
                ),
                OblastDto(
                    oblastType = OblastType.Luhansk,
                    queues = listOf("5.1", "3.1", "2.3", "1.4")
                )
            )

            availableLocationDao.insetAllAvailableLocations(availableLocations)

        }

    }

    private fun checkIsUserDataPresent() {
        viewModelScope.launch {
            val user = userRepository.userData.firstOrNull()

            if (user == null) createUser()
        }
    }

    private suspend fun createUser() {
        val newUserDto = UserDto()
        userRepository.saveNewUserData(newUserDto)
    }

    fun onUIAction(uiAction: UIAction) {
        when (uiAction.actionKey) {
            ANIMATION_END_KEY -> {
                //_navEvent.update { NavigationEvent.NavigateTo(Screen.HomeScreen.route) }

                viewModelScope.launch {
                    Log.d("TEST", "${availableLocationDao.getAvailableLocationsWithQueue()}")
                    Log.d("TEST", "${availableLocationDao.getAvailableLocations()}")
                }
            }
        }
    }



    sealed class Navigation : NavigationPath {
        data class ToErrorDialog(val diiaError: String) : Navigation()
        data object ToHome : Navigation()
        data object ToOnboarding : Navigation()
    }

    private sealed class ActorEvent {
        data class AddJob(val job: SplashJob) : ActorEvent()
        data class MarkAsComplete(val job: SplashJob) : ActorEvent()
        data object ContinueWithJobs : ActorEvent()
    }


    private companion object {
        const val ANIMATION_END_KEY = "animation_end_key"
    }
}