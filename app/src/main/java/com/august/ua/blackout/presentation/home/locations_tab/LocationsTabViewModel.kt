package com.august.ua.blackout.presentation.home.locations_tab

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.august.ua.blackout.data.dvo.LocationDvo
import com.august.ua.blackout.domain.common.parseCalendarDay
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import com.august.ua.blackout.navigation.Screen
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.common.ScreenState
import com.august.ua.blackout.presentation.home.locations_tab.event.LocationsEvent
import com.august.ua.blackout.ui.common.extensions.isNotConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LocationsTabViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val userLocationsRepository: UserLocationsRepository,
) : AndroidViewModel(context as Application) {

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None)
    val screenState = _screenState.asStateFlow()

    private val _locationsState: MutableStateFlow<PagingData<LocationDvo>> = MutableStateFlow(value = PagingData.empty())
    val locationsState: MutableStateFlow<PagingData<LocationDvo>> get() = _locationsState

    init {
        getLocations()
    }

    private fun getLocations() {
        viewModelScope.launch {
            userLocationsRepository.getLocationsOutragePaging()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _locationsState.value = it
                }
        }
    }

    fun onUiEvent(event: LocationsEvent) {
        when (event) {
            LocationsEvent.AddNewLocation -> processAddNewLocation()
            is LocationsEvent.OnLocationClick -> Unit
            is LocationsEvent.OnLocationLongClick -> Unit
            LocationsEvent.ResetScreenState -> resetScreenState()
            LocationsEvent.ResetNavState -> resetNavState()
            LocationsEvent.OnSnackbarDismissed -> resetScreenState()
        }
    }

    private fun resetScreenState() {
        _screenState.update { ScreenState.None }
    }

    private fun resetNavState() {
        _navEvent.update { NavigationEvent.None }
    }

    private fun processAddNewLocation() {
        if (getApplication<Application>().isNotConnected) {
            _screenState.update { ScreenState.NoInternetConnection(
                action = {
                    processAddNewLocation()
                }
            ) }
            return
        }

        _navEvent.update { NavigationEvent.NavigateTo(Screen.CreateUpdateLocation.route) }
    }

    fun performSnackbarAction(action: () -> Unit) {
        resetScreenState()
        action()
    }
}