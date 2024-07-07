package com.august.ua.blackout.presentation.home.locations_tab

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.august.ua.blackout.domain.repository.UserLocationsRepository
import com.august.ua.blackout.navigation.Screen
import com.august.ua.blackout.presentation.common.NavigationEvent
import com.august.ua.blackout.presentation.home.locations_tab.event.LocationsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LocationsTabViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val userLocationsRepository: UserLocationsRepository,
) : AndroidViewModel(context as Application) {

    private val _navEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.None)
    val navEvent = _navEvent.asStateFlow()

    fun onUiEvent(event: LocationsEvent) {
        when (event) {
            LocationsEvent.AddNewLocation -> _navEvent.update { NavigationEvent.NavigateTo(Screen.CreateUpdateLocation.route) }
            is LocationsEvent.OnLocationClick -> TODO()
            is LocationsEvent.OnLocationLongClick -> TODO()
        }
    }
}