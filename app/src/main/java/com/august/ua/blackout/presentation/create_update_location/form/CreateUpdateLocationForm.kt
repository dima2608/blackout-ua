package com.august.ua.blackout.presentation.create_update_location.form

import androidx.annotation.StringRes
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dto.OutragePushTime
import com.august.ua.blackout.data.dto.PushNotificationDto
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType

data class CreateUpdateLocationForm(
    var selectedCity: CityDvo? = null,
    var selectedQueue: String? = null,
    var name: String? = null,
    var selectedIconType: LocationIconType = LocationIconType.Diamond,
    var selectedColorType: LocationColorType = LocationColorType.Red,
    var isPushOn: Boolean = false,
    var selectedPushTime: MutableList<OutragePushTime> = mutableListOf(OutragePushTime.FifteenMin),
    var locationOrdinal: Int = -1,
    var isOutragePushOn: Boolean = true,
    @StringRes
    var oblastTypeError: Int? = null,
    @StringRes
    var queueError: Int? = null,
    var cities: List<CityDvo> = emptyList(),
    @StringRes
    var locationNameError: Int? = null
) {
    fun isOblastSelected() = selectedCity != null
    fun isQueueSelected() =  selectedQueue.isNullOrBlank().not()
    fun isNameValid() = name.isNullOrBlank().not()
}