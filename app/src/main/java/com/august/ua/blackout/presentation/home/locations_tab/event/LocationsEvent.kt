package com.august.ua.blackout.presentation.home.locations_tab.event

import com.august.ua.blackout.data.dvo.LocationDvo

sealed interface LocationsEvent {
    data object AddNewLocation: LocationsEvent
    data class OnLocationClick(val location: LocationDvo): LocationsEvent
    data class OnLocationLongClick(val location: LocationDvo): LocationsEvent
    data object ResetScreenState: LocationsEvent
    data object ResetNavState: LocationsEvent
}