package com.august.ua.blackout.presentation.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.august.ua.blackout.navigation.Screen

const val ONBOARDING_GRAPH_ROUTE_PATTERN = "auth_graph"

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
) {
    navigation(
        route = ONBOARDING_GRAPH_ROUTE_PATTERN,
        startDestination = Screen.OnboardingScreen.route
    ) {

    }
}