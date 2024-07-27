package com.august.ua.blackout.data.mapper

import android.app.Application
import com.august.ua.blackout.data.dto.LocationDto
import com.august.ua.blackout.data.dto.UserDto
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserWithAllLocations

class UserWithAllLocationsToUserDtoMapper(
    data: UserWithAllLocations,
) : Mapper<UserWithAllLocations, UserDto>(data) {

    override fun transform(): UserDto {
        return UserDto(
            id = data?.user?.id,
            isOutrageUpdatePushOn = data?.user?.isOutrageUpdatePushOn ?: false,
            isAllPushTurnOn = data?.user?.isAllPushTurnOn ?: false,
            isNextDayOutragePushOn = data?.user?.isNextDayOutragePushOn ?: false,
            locations = prepareLocations()
        )
    }

    private fun prepareLocations() = data?.locations?.map {
        LocationDto(
            name = it.locationName,
            outragePushTime = it.selectedPushTime,
            queue = it.selectedQueue,
            region = it.selectedLocation
        )
    }
}