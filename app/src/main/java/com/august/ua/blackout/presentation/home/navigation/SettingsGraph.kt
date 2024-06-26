package com.august.ua.blackout.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.august.ua.blackout.navigation.Screen

const val  SETTINGS_GRAPH_ROUTE_PATTERN = " Settings_graph"

fun NavGraphBuilder.settingsGraph(
    navController: NavController,
    externalNavController: NavController,
) {
    navigation(
        route = SETTINGS_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.SettingsScreen.route
    ) {

    }
}

