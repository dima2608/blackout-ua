package com.august.ua.blackout.presentation.home.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CalendarViewDay
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.august.ua.blackout.R

enum class BottomNavigationItems(
    val route: String,
    @StringRes
    val title: Int,
    val icon: ImageVector,
    val iconFill: ImageVector,
    val hasNews: Boolean
) {
    Locations(
        title = R.string.locations,
        icon = Icons.Outlined.Home,
        iconFill = Icons.Filled.Home,
        hasNews = false,
        route = LOCATIONS_GRAPH_ROUTE_PATTERN
    ),
    CALENDAR(
        title = R.string.calendar,
        icon = Icons.Outlined.CalendarViewDay,
        iconFill = Icons.Filled.CalendarViewDay,
        hasNews = false,
        route = CALENDAR_GRAPH_ROUTE_PATTERN
    ),
    SETTINGS(
        title = R.string.settings,
        icon = Icons.Outlined.Settings,
        iconFill = Icons.Filled.Settings,
        hasNews = false,
        route = SETTINGS_GRAPH_ROUTE_PATTERN
    )
}