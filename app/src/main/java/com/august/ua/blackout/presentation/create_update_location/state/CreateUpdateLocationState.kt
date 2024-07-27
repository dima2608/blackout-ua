package com.august.ua.blackout.presentation.create_update_location.state

import androidx.annotation.StringRes
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dto.OblastType
import com.august.ua.blackout.data.dvo.CityDvo
import com.august.ua.blackout.data.dvo.LocationColorType
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.data.dvo.PushTimeDvo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.CityDbo

sealed class CreateUpdateLocationState(
    @StringRes
    var toolbarTitle: Int? = null,
    @StringRes
    val headline: Int? = null,
    @StringRes
    val subtitle: Int? = null,
    @StringRes
    val buttonTitle: Int? = null,
    var lastUpdate: Long = System.currentTimeMillis()
) {
    data object Initial : CreateUpdateLocationState()

    data class LocationState(
        @StringRes
        val toolbar: Int,
        @StringRes
        val groupTitle: Int = R.string.your_region_title,
        val listCities: List<CityDvo>,
        val updateTime: Long = System.currentTimeMillis()
    ) : CreateUpdateLocationState(
        toolbarTitle = toolbar,
        headline = R.string.location_headline,
        subtitle = R.string.location_description,
        buttonTitle = R.string.next_step,
        lastUpdate = updateTime
    )

    data class LocationQueueState(
        @StringRes
        val toolbar: Int,
        @StringRes
        val groupTitle: Int = R.string.your_queue_title,
        val selectedCity: CityDvo,
        val link: String,
        val updateTime: Long = System.currentTimeMillis()
    ) : CreateUpdateLocationState(
        toolbarTitle = toolbar,
        headline = R.string.select_queue_headline,
        subtitle = R.string.select_queue_description,
        buttonTitle = R.string.next_step,
        lastUpdate = updateTime
    )

    data class LocationSettingsState(
        @StringRes
        val toolbar: Int,
        val locationName: String,
        val selectedIconType: LocationIconType,
        val selectedColorType: LocationColorType,
        @StringRes
        val locationNameError: Int?
    ) : CreateUpdateLocationState(
        toolbarTitle = toolbar,
        headline = R.string.location_settings_headline,
        subtitle = R.string.location_settings_headline,
        buttonTitle = R.string.next_step
    )

    data class LocationPushState(
        @StringRes
        val toolbar: Int,
        val pushTimes: List<PushTimeDvo>,
        @StringRes
        val pushGroupTitle: Int = R.string.location_push_on_title,
        @StringRes
        val pushGroupDescription: Int = R.string.location_push_on_description,
        val isOutragePushOn: Boolean,
    ) : CreateUpdateLocationState(
        toolbarTitle = toolbar,
        headline = R.string.location_push_headline,
        subtitle = R.string.location_push_description,
        buttonTitle = R.string.save
    )
}