package com.august.ua.blackout.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.august.ua.blackout.navigation.Screen

const val SEARCH_GRAPH_ROUTE_PATTERN = "Search_graph"

fun NavGraphBuilder.searchGraph(
    navController: NavController,
    externalNavController: NavController,
) {
    navigation(
        route = SEARCH_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.SearchScreen.route
    ) {

    }
}