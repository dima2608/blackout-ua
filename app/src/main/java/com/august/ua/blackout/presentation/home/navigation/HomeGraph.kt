package com.august.ua.blackout.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.august.ua.blackout.navigation.Screen

const val HOME_GRAPH_ROUTE_PATTERN = "Home_graph"

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    externalNavController: NavController,
) {
    navigation(
        route = HOME_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.HomeScreen.route
    ) {

    }
}