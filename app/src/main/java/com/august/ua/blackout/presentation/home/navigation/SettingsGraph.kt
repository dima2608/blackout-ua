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
import com.august.ua.blackout.presentation.home.settings_tab.SettingsTabScreen

const val  SETTINGS_GRAPH_ROUTE_PATTERN = " settings_graph"

fun NavGraphBuilder.settingsGraph(
    navController: NavController,
    externalNavController: NavController,
) {
    navigation(
        route = SETTINGS_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.SettingsTabScreen.route
    ) {
        composable(
            route = Screen.SettingsTabScreen.route
        ) {
            SettingsTabScreen()
        }
    }
}

