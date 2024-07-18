package com.august.ua.blackout.presentation.home.calendar_tab.state

import com.august.ua.blackout.presentation.home.calendar_tab.event.PositionedCalendarEvent
import java.time.LocalDate

sealed class CalendarTabUIState(
    val minDate: LocalDate = LocalDate.now(),
    val maxDate: LocalDate = LocalDate.now(),
    val positionedEvents: List<PositionedCalendarEvent> = emptyList(),
) {

    data object Initial : CalendarTabUIState()

    data object CalendarLoading : CalendarTabUIState()

    data class ShowCalendarUI(
        val calculatedMinDate: LocalDate,
        val calculatedMaxDate: LocalDate,
        val calculatedPositionedEvents: List<PositionedCalendarEvent>,
    ) : CalendarTabUIState(
        minDate = calculatedMinDate,
        maxDate = calculatedMaxDate,
        positionedEvents = calculatedPositionedEvents
    )
}