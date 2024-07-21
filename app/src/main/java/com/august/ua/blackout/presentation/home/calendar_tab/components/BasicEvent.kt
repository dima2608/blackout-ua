package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.august.ua.blackout.domain.common.parseCalendarEventTime
import com.august.ua.blackout.presentation.home.calendar_tab.event.CalendarEvent
import com.august.ua.blackout.presentation.home.calendar_tab.event.EventsProvider
import com.august.ua.blackout.presentation.home.calendar_tab.event.PositionedCalendarEvent
import com.august.ua.blackout.presentation.home.calendar_tab.event.SplitType
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.BlackoutUaTheme
import com.gigamole.composeshadowsplus.rsblur.rsBlurShadow

@Composable
fun BasicEvent(
    positionedCalendarEvent: PositionedCalendarEvent,
    modifier: Modifier = Modifier,
) {
    val event = positionedCalendarEvent.calendarEvent
    val topRadius =
        if (positionedCalendarEvent.splitType == SplitType.Start || positionedCalendarEvent.splitType == SplitType.Both) 0.dp else 4.dp
    val bottomRadius =
        if (positionedCalendarEvent.splitType == SplitType.End || positionedCalendarEvent.splitType == SplitType.Both) 0.dp else 4.dp
    Column(
        modifier = modifier
            .fillMaxSize()
            .rsBlurShadow(
                color = Black.copy(0.1f)
            )
            .padding(
                end = 4.dp,
                bottom = if (positionedCalendarEvent.splitType == SplitType.End) 0.dp else 2.dp,
                top = if (positionedCalendarEvent.splitType == SplitType.End) 0.dp else 2.dp
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
            text = "${event.start.parseCalendarEventTime()} - ${event.end.parseCalendarEventTime()}",
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

@Preview(showBackground = true)
@Composable
fun EventPreview(
    @PreviewParameter(EventsProvider::class) calendarEvent: CalendarEvent,
) {
    BlackoutUaTheme {
        BasicEvent(
            PositionedCalendarEvent(
                calendarEvent,
                SplitType.None,
                calendarEvent.start.toLocalDate(),
                calendarEvent.start.toLocalTime(),
                calendarEvent.end.toLocalTime()
            ),
            modifier = Modifier.sizeIn(maxHeight = 64.dp)
        )
    }
}