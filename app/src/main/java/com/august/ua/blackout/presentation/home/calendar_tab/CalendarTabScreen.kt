package com.august.ua.blackout.presentation.home.calendar_tab

import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.presentation.common.DevicePreviews
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.august.ua.blackout.ui.theme.White
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@Composable
fun CalendarTabScreen(

) {
    CalendarTabContent(

    )
}

val LAYOUT_TOP_PADDING = 30
val LAYOUT_END_PADDING = 50

@Composable
private fun CalendarTabContent(

) {
    Scaffold(

    ) { innerPadding ->
        Schedule(
            modifier = Modifier.statusBarsPadding(),
            events = sampleEvents
        )
    }
}

data class Event(
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null,
)

inline class SplitType private constructor(val value: Int) {
    companion object {
        val None = SplitType(0)
        val Start = SplitType(1)
        val End = SplitType(2)
        val Both = SplitType(3)
    }
}

data class PositionedEvent(
    val event: Event,
    val splitType: SplitType,
    val date: LocalDate,
    val start: LocalTime,
    val end: LocalTime,
    val col: Int = 0,
    val colSpan: Int = 1,
    val colTotal: Int = 1,
)

val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

@Composable
fun BasicEvent(
    positionedEvent: PositionedEvent,
    modifier: Modifier = Modifier,
) {
    val event = positionedEvent.event
    val topRadius =
        if (positionedEvent.splitType == SplitType.Start || positionedEvent.splitType == SplitType.Both) 0.dp else 4.dp
    val bottomRadius =
        if (positionedEvent.splitType == SplitType.End || positionedEvent.splitType == SplitType.Both) 0.dp else 4.dp
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                end = 2.dp,
                bottom = if (positionedEvent.splitType == SplitType.End) 0.dp else 2.dp
            )
            .clipToBounds()
            .background(
                event.color,
                shape = RoundedCornerShape(
                    topStart = topRadius,
                    topEnd = topRadius,
                    bottomEnd = bottomRadius,
                    bottomStart = bottomRadius,
                )
            )
            .padding(4.dp)
    ) {
        Text(
            text = "${event.start.format(EventTimeFormatter)} - ${
                event.end.format(
                    EventTimeFormatter
                )
            }",
            style = BlackoutTextStyle.t5TextSmallDescription,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )

        Text(
            text = event.name,
            style = BlackoutTextStyle.heroText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        if (event.description != null) {
            Text(
                text = event.description,
                style = BlackoutTextStyle.hero2Text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

private val sampleEvents = listOf(
    Event(
        name = "Google I/O Keynote",
        color = Color(0xFFAFBBF2),
        start = LocalDateTime.parse("2021-05-18T09:00:00"),
        end = LocalDateTime.parse("2021-05-18T11:00:00"),
        description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
    ),
    Event(
        name = "Developer Keynote",
        color = Color(0xFFAFBBF2),
        start = LocalDateTime.parse("2021-05-18T09:00:00"),
        end = LocalDateTime.parse("2021-05-18T11:00:00"),
        description = "Learn about the latest updates to our developer products and platforms from Google Developers.",
    ),
    Event(
        name = "What's new in Android",
        color = Color(0xFF1B998B),
        start = LocalDateTime.parse("2021-05-18T09:00:00"),
        end = LocalDateTime.parse("2021-05-18T11:00:00"),
        description = "In this Keynote, Chet Haase, Dan Sandler, and Romain Guy discuss the latest Android features and enhancements for developers.",
    ),
    Event(
        name = "What's new in Material Design",
        color = Color(0xFF6DD3CE),
        start = LocalDateTime.parse("2021-05-18T09:00:00"),
        end = LocalDateTime.parse("2021-05-18T11:00:00"),
        description = "Learn about the latest design improvements to help you build personal dynamic experiences with Material Design.",
    ),
    Event(
        name = "What's new in Machine Learning",
        color = Color(0xFFF4BFDB),
        start = LocalDateTime.parse("2021-05-18T09:00:00"),
        end = LocalDateTime.parse("2021-05-18T11:00:00"),
        description = "Learn about the latest and greatest in ML from Google. We’ll cover what’s available to developers when it comes to creating, understanding, and deploying models for a variety of different applications.",
    ),
    Event(
        name = "What's new in Machine Learning",
        color = Color(0xFFF4BFDB),
        start = LocalDateTime.parse("2021-05-18T19:00:00"),
        end = LocalDateTime.parse("2021-05-18T20:00:00"),
        description = "Learn about the latest and greatest in ML from Google. We’ll cover what’s available to developers when it comes to creating, understanding, and deploying models for a variety of different applications.",
    )
)

class EventsProvider : PreviewParameterProvider<Event> {
    override val values = sampleEvents.asSequence()
}

@Preview(showBackground = true)
@Composable
fun EventPreview(
    @PreviewParameter(EventsProvider::class) event: Event,
) {
    BlackoutUaTheme {
        BasicEvent(
            PositionedEvent(
                event,
                SplitType.None,
                event.start.toLocalDate(),
                event.start.toLocalTime(),
                event.end.toLocalTime()
            ),
            modifier = Modifier.sizeIn(maxHeight = 64.dp)
        )
    }
}

private class EventDataModifier(
    val positionedEvent: PositionedEvent,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = positionedEvent
}

private fun Modifier.eventData(positionedEvent: PositionedEvent) =
    this.then(EventDataModifier(positionedEvent))

private val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")
private val DayOfWeekFormatter = DateTimeFormatter.ofPattern("EE")
private val DayNumberFormatter = DateTimeFormatter.ofPattern("d")

@Composable
fun BasicDayHeader(
    day: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = day.format(DayFormatter),
        textAlign = TextAlign.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BasicDayHeaderPreview() {
    BlackoutUaTheme {
        BasicDayHeader(day = LocalDate.now())
    }
}

@Composable
fun ScheduleHeader(
    minDate: LocalDate,
    maxDate: LocalDate,
    dayWidth: Dp,
    modifier: Modifier = Modifier,
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
) {
    val date = LocalDate.now()
    val dayOfWeek = date.format(DayOfWeekFormatter)
    val dayNumber = date.format(DayNumberFormatter)
    Box(modifier = modifier
        .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = dayOfWeek,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleHeaderPreview() {
    BlackoutUaTheme {
        ScheduleHeader(
            minDate = LocalDate.now(),
            maxDate = LocalDate.now().plusDays(5),
            dayWidth = 256.dp,
        )
    }
}

private val HourFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun BasicSidebarLabel(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Text(
        text = time.format(HourFormatter),
        modifier = modifier
            .fillMaxHeight(),
        style = BlackoutTextStyle.t3TextBody,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun BasicSidebarLabelPreview() {
    BlackoutUaTheme {
        BasicSidebarLabel(time = LocalTime.NOON, Modifier.sizeIn(maxHeight = 64.dp))
    }
}

@Composable
fun ScheduleSidebar(
    hourHeight: Dp,
    modifier: Modifier = Modifier,
    minTime: LocalTime = LocalTime.MIN,
    maxTime: LocalTime = LocalTime.MAX,
    label: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
) {
    val numMinutes = ChronoUnit.MINUTES.between(minTime, maxTime).toInt() + 1
    val numHours = numMinutes / 60
    val firstHour = minTime.truncatedTo(ChronoUnit.HOURS)
    val firstHourOffsetMinutes =
        if (firstHour == minTime) 0 else ChronoUnit.MINUTES.between(minTime, firstHour.plusHours(1))
    val firstHourOffset = hourHeight * (firstHourOffsetMinutes / 60f)
    val startTime = if (firstHour == minTime) firstHour else firstHour.plusHours(1)
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(LAYOUT_TOP_PADDING.dp))
        repeat(numHours) { i ->
            Box(
                modifier = Modifier
                    .height(hourHeight)
                    .padding(horizontal = 8.dp)
            ) {
                label(startTime.plusHours(i.toLong()))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleSidebarPreview() {
    BlackoutUaTheme {
        ScheduleSidebar(hourHeight = 64.dp)
    }
}

private fun splitEvents(events: List<Event>): List<PositionedEvent> {
    return events
        .map { event ->
            val startDate = event.start.toLocalDate()
            val endDate = event.end.toLocalDate()
            if (startDate == endDate) {
                listOf(
                    PositionedEvent(
                        event,
                        SplitType.None,
                        event.start.toLocalDate(),
                        event.start.toLocalTime(),
                        event.end.toLocalTime()
                    )
                )
            } else {
                val days = ChronoUnit.DAYS.between(startDate, endDate)
                val splitEvents = mutableListOf<PositionedEvent>()
                for (i in 0..days) {
                    val date = startDate.plusDays(i)
                    splitEvents += PositionedEvent(
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

private fun PositionedEvent.overlapsWith(other: PositionedEvent): Boolean {
    return date == other.date && start < other.end && end > other.start
}

private fun List<PositionedEvent>.timesOverlapWith(event: PositionedEvent): Boolean {
    return any { it.overlapsWith(event) }
}

private fun arrangeEvents(events: List<PositionedEvent>): List<PositionedEvent> {
    val positionedEvents = mutableListOf<PositionedEvent>()
    val groupEvents: MutableList<MutableList<PositionedEvent>> = mutableListOf()

    fun resetGroup() {
        groupEvents.forEachIndexed { colIndex, col ->
            col.forEach { e ->
                positionedEvents.add(e.copy(col = colIndex, colTotal = groupEvents.size))
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
    return positionedEvents
}

sealed class ScheduleSize {
    class FixedSize(val size: Dp) : ScheduleSize()
    class FixedCount(val count: Float) : ScheduleSize() {
        constructor(count: Int) : this(count.toFloat())
    }

    class Adaptive(val minSize: Dp) : ScheduleSize()
}

@Composable
fun Schedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (positionedEvent: PositionedEvent) -> Unit = {
        BasicEvent(
            positionedEvent = it
        )
    },
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
    timeLabel: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
    minDate: LocalDate = events.minByOrNull(Event::start)?.start?.toLocalDate() ?: LocalDate.now(),
    maxDate: LocalDate = events.maxByOrNull(Event::end)?.end?.toLocalDate() ?: LocalDate.now(),
    minTime: LocalTime = LocalTime.MIN,
    maxTime: LocalTime = LocalTime.MAX,
    daySize: ScheduleSize = ScheduleSize.FixedCount(1),
    hourSize: ScheduleSize = ScheduleSize.FixedSize(64.dp),
) {
    val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
    val numMinutes = ChronoUnit.MINUTES.between(minTime, maxTime).toInt() + 1
    val numHours = numMinutes.toFloat() / 60f
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    var sidebarWidth by remember { mutableStateOf(0) }
    var headerHeight by remember { mutableStateOf(0) }
    BoxWithConstraints(modifier = modifier) {
//        val dayWidth: Dp = when (daySize) {
//            is ScheduleSize.FixedSize -> daySize.size
//            is ScheduleSize.FixedCount -> with(LocalDensity.current) { ((constraints.maxWidth - sidebarWidth) / daySize.count).toDp() }
//            is ScheduleSize.Adaptive -> with(LocalDensity.current) {
//                maxOf(
//                    ((constraints.maxWidth - sidebarWidth) / numDays).toDp(),
//                    daySize.minSize
//                )
//            }
//        }
        val dayWidth: Dp = with(LocalDensity.current) { (constraints.maxWidth - sidebarWidth - LAYOUT_END_PADDING).toDp() }

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
                minDate = minDate,
                maxDate = maxDate,
                dayWidth = dayWidth,
                dayHeader = dayHeader,
                modifier = Modifier
                    .onGloballyPositioned { headerHeight = it.size.height + LAYOUT_TOP_PADDING }
            )
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
                    events = events,
                    eventContent = eventContent,
                    minDate = minDate,
                    maxDate = maxDate,
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

@Composable
fun BasicSchedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (positionedEvent: PositionedEvent) -> Unit = {
        BasicEvent(
            positionedEvent = it,
            modifier = modifier
        )
    },
    minDate: LocalDate = events.minByOrNull(Event::start)?.start?.toLocalDate() ?: LocalDate.now(),
    maxDate: LocalDate = events.maxByOrNull(Event::end)?.end?.toLocalDate() ?: LocalDate.now(),
    minTime: LocalTime = LocalTime.MIN,
    maxTime: LocalTime = LocalTime.MAX,
    dayWidth: Dp,
    hourHeight: Dp,
) {
    val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
    val numMinutes = ChronoUnit.MINUTES.between(minTime, maxTime).toInt() + 1
    val numHours = numMinutes / 60
    val dividerColor = Black.copy(0.25f)
    val positionedEvents = remember(events) { arrangeEvents(splitEvents(events.sortedBy(Event::start))).filter { it.end > minTime && it.start < maxTime } }
    Layout(
        content = {
            positionedEvents.forEach { positionedEvent ->
                Box(
                    modifier = Modifier
                        .eventData(positionedEvent)
                ) {
                    eventContent(positionedEvent)
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .offset(y = 5.dp)
            .drawBehind {
                val firstHour = minTime.truncatedTo(ChronoUnit.HOURS)
                val firstHourOffsetMinutes =
                    if (firstHour == minTime) 0 else ChronoUnit.MINUTES.between(
                        minTime,
                        firstHour.plusHours(1)
                    )
                val firstHourOffset =
                    ((firstHourOffsetMinutes + LAYOUT_TOP_PADDING )/ 60f) * hourHeight.toPx()

                repeat(numHours) {
                    drawLine(
                        dividerColor,
                        start = Offset(0f, it * hourHeight.toPx() + firstHourOffset),
                        end = Offset(size.width, it * hourHeight.toPx() + firstHourOffset),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                repeat(numDays - 1) {
                    drawLine(
                        dividerColor,
                        start = Offset((it + 1) * dayWidth.toPx(), 0f),
                        end = Offset((it + 1) * dayWidth.toPx(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                drawLine(
                    dividerColor,
                    start = Offset(20f, 0f),
                    end = Offset(20f, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
    ) { measureables, constraints ->
        val eventStartPadding = 40
        val totalWidth = constraints.maxWidth
        val height = ((hourHeight.toPx()) * (numMinutes / 60f)).roundToInt()
        val width = totalWidth / numDays
        val placeablesWithEvents = measureables.map { measurable ->
            val splitEvent = measurable.parentData as PositionedEvent
            val eventDurationMinutes =
                ChronoUnit.MINUTES.between(splitEvent.start, minOf(splitEvent.end, maxTime))
            val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
            val eventWidth =
                ((splitEvent.colSpan.toFloat() / splitEvent.colTotal.toFloat()) * dayWidth.toPx()).roundToInt()
            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = eventWidth,
                    maxWidth = eventWidth,
                    minHeight = eventHeight,
                    maxHeight = eventHeight
                )
            )
            Pair(placeable, splitEvent)
        }
        layout(width, height) {
            placeablesWithEvents.forEach { (placeable, splitEvent) ->
                val eventOffsetMinutes = if (splitEvent.start > minTime) ChronoUnit.MINUTES.between(
                    minTime,
                    splitEvent.start
                ) else 0
                val eventY = (((eventOffsetMinutes + LAYOUT_TOP_PADDING) / 60f) * hourHeight.toPx()).roundToInt()
                val eventOffsetDays = ChronoUnit.DAYS.between(minDate, splitEvent.date).toInt()
                val eventX =
                    eventOffsetDays * dayWidth.roundToPx() + (splitEvent.col * (dayWidth.toPx() / splitEvent.colTotal.toFloat())).roundToInt() + eventStartPadding
                placeable.place(eventX, eventY)
            }
        }
    }
}

@DevicePreviews
@Composable
fun SchedulePreview() {
    BlackoutUaTheme {
        Schedule(
            sampleEvents,
            modifier = Modifier.fillMaxWidth()
        )
    }
}