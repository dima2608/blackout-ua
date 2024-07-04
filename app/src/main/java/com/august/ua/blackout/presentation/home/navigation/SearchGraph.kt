package com.august.ua.blackout.presentation.home.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.august.ua.blackout.navigation.Screen

const val CALENDAR_GRAPH_ROUTE_PATTERN = "calendar_graph"

fun NavGraphBuilder.calendarGraph(
    navController: NavController,
    externalNavController: NavController,
) {
    navigation(
        route = CALENDAR_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.CalendarTabScreen.route
    ) {
        composable(
            route = Screen.CalendarTabScreen.route
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Cyan)
            ) {
                Text(text = "CALENDAR_GRAPH_ROUTE_PATTERN")
            }
        }
    }
}