package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.presentation.home.calendar_tab.event.PositionedCalendarEvent
import com.august.ua.blackout.presentation.home.calendar_tab.event.ScheduleSize
import com.august.ua.blackout.presentation.home.calendar_tab.state.CalendarTabUIState
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit

@Composable
fun Schedule(
    modifier: Modifier = Modifier,
    eventContent: @Composable (positionedCalendarEvent: PositionedCalendarEvent) -> Unit = {
        BasicEvent(
            positionedCalendarEvent = it
        )
    },
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
    timeLabel: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
    minTime: LocalTime = LocalTime.MIN,
    maxTime: LocalTime = LocalTime.MAX,
    daySize: ScheduleSize = ScheduleSize.FixedCount(1),
    hourSize: ScheduleSize = ScheduleSize.FixedSize(64.dp),
    uiState: CalendarTabUIState,
) {
    //val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
    val numMinutes = ChronoUnit.MINUTES.between(minTime, maxTime).toInt() + 1
    val numHours = numMinutes.toFloat() / 60f
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    var sidebarWidth by remember { mutableStateOf(0) }
    var headerHeight by remember { mutableStateOf(0) }
    BoxWithConstraints(modifier = modifier) {

        val dayWidth: Dp =
            with(LocalDensity.current) { (constraints.maxWidth - sidebarWidth - LAYOUT_END_PADDING).toDp() }

        val hourHeight: Dp = when (hourSize) {
            is ScheduleSize.FixedSize -> hourSize.size
            is ScheduleSize.FixedCount -> with(LocalDensity.current) { ((constraints.maxHeight - headerHeight) / hourSize.count).toDp() }
            is ScheduleSize.Adaptive -> with(LocalDensity.current) {
                maxOf(
                    ((constraints.maxHeight - headerHeight) / numHours).toDp(),
                    hourSize.minSize
                )
            }
        }
        Column(modifier = modifier) {
            ScheduleHeader(
                sideBarWith = sidebarWidth,
                modifier = Modifier
                    .onGloballyPositioned { headerHeight = it.size.height + LAYOUT_TOP_PADDING }
            )
            if (uiState is CalendarTabUIState.CalendarLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                ScheduleSidebar(
                    hourHeight = hourHeight,
                    minTime = minTime,
                    maxTime = maxTime,
                    label = timeLabel,
                    modifier = Modifier
                        .verticalScroll(verticalScrollState)
                        .onGloballyPositioned { sidebarWidth = it.size.width }
                )
                BasicSchedule(
                    positionedEvents = uiState.positionedEvents,
                    eventContent = eventContent,
                    minDate = uiState.minDate,
                    maxDate = uiState.maxDate,
                    minTime = minTime,
                    maxTime = maxTime,
                    dayWidth = dayWidth,
                    hourHeight = hourHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(verticalScrollState)
                )
            }
        }
    }
}

@DevicePreviews
@Composable
fun SchedulePreview() {
    BlackoutUaTheme {
//        Schedule(
//            sampleCalendarEvents,
//            modifier = Modifier.fillMaxWidth()
//        )
    }
}