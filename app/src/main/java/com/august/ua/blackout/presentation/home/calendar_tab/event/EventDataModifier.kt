package com.august.ua.blackout.presentation.home.calendar_tab.event

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

private class EventDataModifier(
    val positionedCalendarEvent: PositionedCalendarEvent,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = positionedCalendarEvent
}

fun Modifier.eventData(positionedCalendarEvent: PositionedCalendarEvent) =
    this.then(EventDataModifier(positionedCalendarEvent))