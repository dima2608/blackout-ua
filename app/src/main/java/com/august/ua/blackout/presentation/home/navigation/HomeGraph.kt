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
import com.august.ua.blackout.presentation.home.locations_tab.LocationsTabScreen

const val LOCATIONS_GRAPH_ROUTE_PATTERN = "locations_graph"

fun NavGraphBuilder.locationsGraph(
    navController: NavController,
    externalNavController: NavController,
) {
    navigation(
        route = LOCATIONS_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.LocationsTabScreen.route
    ) {
       composable(
           route =  Screen.LocationsTabScreen.route
       ) {
           LocationsTabScreen(
               navigateTo = {
                   externalNavController.navigate(it)
               }
           )
       }
    }
}