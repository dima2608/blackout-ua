package com.august.ua.blackout.presentation.home.calendar_tab.event

import java.time.LocalDate
import java.time.LocalTime

data class PositionedCalendarEvent(
    val calendarEvent: CalendarEvent,
    val splitType: SplitType,
    val date: LocalDate,
    val start: LocalTime,
    val end: LocalTime,
    val col: Int = 0,
    val colSpan: Int = 1,
    val colTotal: Int = 1,
)

fun PositionedCalendarEvent.overlapsWith(other: PositionedCalendarEvent): Boolean {
    return date == other.date && start < other.end && end > other.start
}

fun List<PositionedCalendarEvent>.timesOverlapWith(event: PositionedCalendarEvent): Boolean {
    return any { it.overlapsWith(event) }
}