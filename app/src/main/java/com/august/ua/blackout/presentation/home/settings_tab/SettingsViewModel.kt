package com.august.ua.blackout.presentation.home.settings_tab

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.domain.repository.BlackoutRepository
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import com.august.ua.blackout.domain.repository.user.UserRepository
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.create_update_location.CreateUpdateLocationPage.Undefined
import com.august.ua.blackout.presentation.create_update_location.form.CreateUpdateLocationForm
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState.Initial
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val blackoutRepository: BlackoutRepository,
    private val locationsRepository: UserLocationsRepository,
    private val userRepository: UserRepository<Flow<UserDto>, UserDto>
) : AndroidViewModel(context as Application) {

    private var currentPage = Undefined
    private val form = CreateUpdateLocationForm()

    private val _uiState = MutableStateFlow<CreateUpdateLocationState>(Initial)
    val uiState: StateFlow<CreateUpdateLocationState> = _uiState.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None)
    val screenState = _screenState.asStateFlow()

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()
}