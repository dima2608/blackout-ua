package com.august.ua.blackout.domain.common

import android.text.format.DateFormat
import okhttp3.internal.format
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")
private val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")
private val DayOfWeekFormatter = DateTimeFormatter.ofPattern("EE")
private val DayNumberFormatter = DateTimeFormatter.ofPattern("d")
private val HourFormatter = DateTimeFormatter.ofPattern("HH:mm")
private val DayTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")

fun LocalDate.parseCalendarDay(): String  = format(DayFormatter)
fun LocalDate.parseCalendarDayOfWeek(): String  = format(DayOfWeekFormatter)
fun LocalDate.parseCalendarDayNumber(): String  = format(DayNumberFormatter)
fun LocalTime.parseCalendarHourNumber(): String  = format(HourFormatter)
fun LocalDateTime.parseCalendarEventTime(): String = format(EventTimeFormatter)

fun ZonedDateTime.parseDayTime(): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    return format(formatter)
}
