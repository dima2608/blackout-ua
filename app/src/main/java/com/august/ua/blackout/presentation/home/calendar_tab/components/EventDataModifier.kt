package com.august.ua.blackout.presentation.home.calendar_tab.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import com.august.ua.blackout.presentation.home.calendar_tab.event.PositionedCalendarEvent

private class EventDataModifier(
    val positionedCalendarEvent: PositionedCalendarEvent,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = positionedCalendarEvent
}

fun Modifier.eventData(positionedCalendarEvent: PositionedCalendarEvent) =
    this.then(EventDataModifier(positionedCalendarEvent))