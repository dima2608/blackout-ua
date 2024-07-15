package com.august.ua.blackout.data.mapper

import android.content.Context
import com.august.ua.blackout.R
import com.august.ua.blackout.data.dvo.ElectricityStatus
import com.august.ua.blackout.data.dvo.LocationDvo
import com.august.ua.blackout.data.dvo.LocationIconType
import com.august.ua.blackout.data.local.db.dbo.with_embeded.UserLocationOutrageDbo
import com.august.ua.blackout.domain.common.EMPTY_STRING
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class UserLocationOutrageDboToLocationDvoMapper(
    data: UserLocationOutrageDbo,
    private val context: Context,
) : Mapper<UserLocationOutrageDbo, LocationDvo>(data) {

    override fun transform(): LocationDvo {
        return  LocationDvo(
            canBeCollapsed = data?.locationOrder != 1,
            icon = data?.locationIconType?.icon ?: LocationIconType.Diamond.icon,
            isCollapsedState = data?.locationOrder != 1,
            lightTurnOnIn = calculateLightTurnOnIn(),
            locationName = data?.locationName.toString(),
            period = "${context.getString(R.string.period)} ${calculatePeriod()}",
            queueAndLocation = "${data?.selectedQueue} ${context.getString(R.string.queue)} (${data?.selectedLocation?.oblastName?.let { context.getString(it)}})",
            status = calculateStatus()

        )
    }

    private fun calculateLightTurnOnIn(): String {
        try {
            val currentTime = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            val sortedShifts = data?.shifts?.sortedBy { LocalTime.parse(it.start, formatter) }
            val currentStatus = calculateStatus()

            return if (currentStatus == ElectricityStatus.Unavailable) {
                val currentShift = data?.shifts?.find { shift ->
                    val startTime = LocalTime.parse(shift.start, formatter)
                    val endTime = LocalTime.parse(shift.end, formatter)
                    currentTime.isAfter(startTime) && currentTime.isBefore(endTime)
                }
                val endTime = LocalTime.parse(currentShift?.end, formatter)
                val duration = Duration.between(currentTime, endTime)
                formatDuration(duration)
            } else {
                val nextShift = sortedShifts?.find { shift ->
                    val startTime = LocalTime.parse(shift.start, formatter)
                    currentTime.isBefore(startTime)
                }
                val startTime = LocalTime.parse(nextShift?.start, formatter)
                val duration = Duration.between(currentTime, startTime)
                formatDuration(duration)
            }
        } catch (e: Exception) {
            return "N/A"
        }
    }

    private fun formatDuration(duration: Duration): String {
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        return if (hours > 0) {
            "${hours}${context.getString(R.string.hour_short)} ${minutes}${context.getString(R.string.minutes_short)}"
        } else {
            "${minutes}${context.getString(R.string.minutes_short)}"
        }
    }

    private fun calculatePeriod(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        val sortedShifts = data?.shifts?.sortedBy { LocalTime.parse(it.start, formatter) }
        val currentShift = sortedShifts?.find { shift ->
            val startTime = LocalTime.parse(shift.start, formatter)
            val endTime = LocalTime.parse(shift.end, formatter)
            currentTime.isAfter(startTime) && currentTime.isBefore(endTime)
        }

        return if (currentShift != null) {
            // ElectricityStatus.Unavailable
            val startTime = LocalTime.parse(currentShift.start, formatter)
            val endTime = LocalTime.parse(currentShift.end, formatter)
            "$startTime - $endTime"
        } else {
            // ElectricityStatus.Available
            val previousShift = sortedShifts?.findLast { shift ->
                val endTime = LocalTime.parse(shift.end, formatter)
                currentTime.isAfter(endTime)
            }

            val nextShift = sortedShifts?.find { shift ->
                val startTime = LocalTime.parse(shift.start, formatter)
                currentTime.isBefore(startTime)
            }

            val previousEnd = previousShift?.let { LocalTime.parse(it.end, formatter).toString() } ?: "N/A"
            val nextStart = nextShift?.let { LocalTime.parse(it.start, formatter).toString() } ?: "N/A"
            "$previousEnd - $nextStart"
        }
    }

    private fun calculateStatus(): ElectricityStatus {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        for (shift in data?.shifts.orEmpty()) {
            val startTime = LocalTime.parse(shift.start, formatter)
            val endTime = LocalTime.parse(shift.end, formatter)
            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                return ElectricityStatus.Unavailable
            }
        }
        return ElectricityStatus.Available
    }
}