package com.august.ua.blackout.presentation.create_update_location.form

import androidx.annotation.StringRes
import com.august.ua.blackout.data.dto.OblastType
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
    var locationOrdinal: Int = -1,
    @StringRes
    var oblastTypeError: Int? = null,
    @StringRes
    var queueError: Int? = null,
    var cities: List<CityDvo> = emptyList(),
) {
    fun isOblastSelected() = selectedCity != null
    fun isQueueSelected() =  selectedQueue.isNullOrBlank().not()
    fun isNameValid() = name.isNullOrBlank().not()
}