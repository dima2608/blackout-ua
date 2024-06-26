package com.august.ua.blackout.presentation.home.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.august.ua.blackout.R
import com.august.ua.blackout.navigation.Screen

enum class BottomNavigationItems(
    val route: String,
    @StringRes
    val title: Int,
    val icon: ImageVector,
    val hasNews: Boolean
) {
    HOME(
        title = R.string.home,
        icon = Icons.Outlined.Home,
        hasNews = true,
        route =HOME_GRAPH_ROUTE_PATTERN
    ),
    SEARCH(
        title = R.string.search,
        icon = Icons.Outlined.Search,
        hasNews = false,
        route = SEARCH_GRAPH_ROUTE_PATTERN
    ),
    SETTINGS(
        title = R.string.settings,
        icon = Icons.Outlined.Settings,
        hasNews = false,
        route = SETTINGS_GRAPH_ROUTE_PATTERN
    )
}