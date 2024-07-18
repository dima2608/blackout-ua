package com.august.ua.blackout.presentation.home.calendar_tab

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState
import com.august.ua.blackout.presentation.create_update_location.state.CreateUpdateLocationState.Initial
import com.august.ua.blackout.presentation.home.calendar_tab.event.CalendarEvent
import com.august.ua.blackout.presentation.home.calendar_tab.event.PositionedCalendarEvent
import com.august.ua.blackout.presentation.home.calendar_tab.event.SplitType
import com.august.ua.blackout.presentation.home.calendar_tab.event.overlapsWith
import com.august.ua.blackout.presentation.home.calendar_tab.event.sampleCalendarEvents
import com.august.ua.blackout.presentation.home.calendar_tab.event.timesOverlapWith
import com.august.ua.blackout.presentation.home.calendar_tab.state.CalendarTabUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class CalendarTabViewModel @Inject constructor(
    @ApplicationContext context: Context,

    ) : AndroidViewModel(context as Application) {

    private val _uiState = MutableStateFlow<CalendarTabUIState>(CalendarTabUIState.Initial)
    val uiState: StateFlow<CalendarTabUIState> = _uiState.asStateFlow()

    private val calendarEvents = sampleCalendarEvents.toMutableList()
    private val minTime: LocalTime = LocalTime.MIN
    private val maxTime: LocalTime = LocalTime.MAX

    init {
        initializeCalendar()
    }

    private fun initializeCalendar() {
        viewModelScope.launch {
            _uiState.update { CalendarTabUIState.CalendarLoading }
            delay(1000)
            prepareCalendarScheduleUI()
        }
    }


    private fun prepareCalendarScheduleUI() {
        val minDate = calendarEvents.minByOrNull(CalendarEvent::start)?.start?.toLocalDate()
            ?: LocalDate.now()
        val maxDate =
            calendarEvents.maxByOrNull(CalendarEvent::end)?.end?.toLocalDate() ?: LocalDate.now()
        val state = CalendarTabUIState.ShowCalendarUI(
            calculatedPositionedEvents = calculatedPositionedEvents(),
            calculatedMaxDate = maxDate,
            calculatedMinDate = minDate
        )

        _uiState.update { state }
    }

    private fun calculatedPositionedEvents() =
        arrangeEvents(splitEvents(calendarEvents.sortedBy(CalendarEvent::start))).filter { it.end > minTime && it.start < maxTime }

    private fun splitEvents(calendarEvents: List<CalendarEvent>): List<PositionedCalendarEvent> {
        return calendarEvents
            .map { event ->
                val startDate = event.start.toLocalDate()
                val endDate = event.end.toLocalDate()
                if (startDate == endDate) {
                    listOf(
                        PositionedCalendarEvent(
                            event,
                            SplitType.None,
                            event.start.toLocalDate(),
                            event.start.toLocalTime(),
                            event.end.toLocalTime()
                        )
                    )
                } else {
                    val days = ChronoUnit.DAYS.between(startDate, endDate)
                    val splitEvents = mutableListOf<PositionedCalendarEvent>()
                    for (i in 0..days) {
                        val date = startDate.plusDays(i)
                        splitEvents += PositionedCalendarEvent(
                            event,
                            splitType = if (date == startDate) SplitType.End else if (date == endDate) SplitType.Start else SplitType.Both,
                            date = date,
                            start = if (date == startDate) event.start.toLocalTime() else LocalTime.MIN,
                            end = if (date == endDate) event.end.toLocalTime() else LocalTime.MAX,
                        )
                    }
                    splitEvents
                }
            }
            .flatten()
    }

    private fun arrangeEvents(events: List<PositionedCalendarEvent>): List<PositionedCalendarEvent> {
        val positionedCalendarEvents = mutableListOf<PositionedCalendarEvent>()
        val groupEvents: MutableList<MutableList<PositionedCalendarEvent>> = mutableListOf()

        fun resetGroup() {
            groupEvents.forEachIndexed { colIndex, col ->
                col.forEach { e ->
                    positionedCalendarEvents.add(
                        e.copy(
                            col = colIndex,
                            colTotal = groupEvents.size
                        )
                    )
                }
            }
            groupEvents.clear()
        }

        events.forEach { event ->
            var firstFreeCol = -1
            var numFreeCol = 0
            for (i in 0 until groupEvents.size) {
                val col = groupEvents[i]
                if (col.timesOverlapWith(event)) {
                    if (firstFreeCol < 0) continue else break
                }
                if (firstFreeCol < 0) firstFreeCol = i
                numFreeCol++
            }

            when {
                // Overlaps with all, add a new column
                firstFreeCol < 0 -> {
                    groupEvents += mutableListOf(event)
                    // Expand anything that spans into the previous column and doesn't overlap with this event
                    for (ci in 0 until groupEvents.size - 1) {
                        val col = groupEvents[ci]
                        col.forEachIndexed { ei, e ->
                            if (ci + e.colSpan == groupEvents.size - 1 && !e.overlapsWith(event)) {
                                col[ei] = e.copy(colSpan = e.colSpan + 1)
                            }
                        }
                    }
                }
                // No overlap with any, start a new group
                numFreeCol == groupEvents.size -> {
                    resetGroup()
                    groupEvents += mutableListOf(event)
                }
                // At least one column free, add to first free column and expand to as many as possible
                else -> {
                    groupEvents[firstFreeCol] += event.copy(colSpan = numFreeCol)
                }
            }
        }
        resetGroup()
        return positionedCalendarEvents
    }


//    minDate: LocalDate = calendarEvents.minByOrNull(CalendarEvent::start)?.start?.toLocalDate() ?: LocalDate.now(),
//    maxDate: LocalDate = calendarEvents.maxByOrNull(CalendarEvent::end)?.end?.toLocalDate() ?: LocalDate.now(),

//BasicShedule
//    minDate: LocalDate = calendarEvents.minByOrNull(CalendarEvent::start)?.start?.toLocalDate() ?: LocalDate.now(),
//    maxDate: LocalDate = calendarEvents.maxByOrNull(CalendarEvent::end)?.end?.toLocalDate() ?: LocalDate.now(),
}