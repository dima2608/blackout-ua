package com.august.ua.blackout.presentation.create_update_location.event

import com.august.ua.blackout.data.dto.OutragePushTime
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.presentation.onboarding.event.OnboardingEvent

sealed interface CreateUpdateLocationEvent {
    data object NextScreen: CreateUpdateLocationEvent
    data object PreviousScreen: CreateUpdateLocationEvent

    data class CityChanged(val city: CityDvo): CreateUpdateLocationEvent

    data class QueueChanged(val queue: String): CreateUpdateLocationEvent

    data class NameChanged(val name: String): CreateUpdateLocationEvent
    data class IconChanged(val iconType: LocationIconType): CreateUpdateLocationEvent
    data class ColorChanged(val colorType: LocationColorType): CreateUpdateLocationEvent

    data class OnPushOn(val type: OutragePushTime, val isOn: Boolean): CreateUpdateLocationEvent

    data class OnOutrageUpdatePushOn(val isOn: Boolean): CreateUpdateLocationEvent

    data object OnSnackbarDismissed: CreateUpdateLocationEvent
    data object OnSnackbarRetry: CreateUpdateLocationEvent
    data object ResetScreenState: CreateUpdateLocationEvent
}