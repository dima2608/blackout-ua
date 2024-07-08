package com.august.ua.blackout.data.dto

import android.os.Parcelable
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationShiftDbo
import com.august.ua.blackout.domain.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OutrageDto(
    @SerializedName("type")
    val type: OutrageStatus?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("region")
    val region: OblastType?,
    @SerializedName("changeCount")
    val changeCount: Int?,
    @SerializedName("shifts")
    val shifts: List<ShiftDto>?,
): Entity, Parcelable

fun List<OutrageDto>?.mapToUserLocationShiftListDbo(
    oblastType: OblastType?,
    selectedQueue: String?
) = this?.filter { outrage ->
    outrage.region == oblastType && outrage.shifts?.any { shift ->
        shift.queues?.any { it.queue == selectedQueue } == true
    } == true
}?.flatMap { outrage ->
    outrage.shifts?.filter { shift ->
        shift.queues?.any { it.queue == selectedQueue } == true
    }?.map { shift ->
        UserLocationShiftDbo(
            start = shift.start,
            end = shift.end,
            lightStatus = shift.queues?.first { it.queue == selectedQueue }?.lightStatus ?: -1
        )
    }.orEmpty()
}.orEmpty()