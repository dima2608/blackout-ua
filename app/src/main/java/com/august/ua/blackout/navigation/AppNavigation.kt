package com.august.ua.blackout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.august.ua.blackout.presentation.home.HomeScreen
import com.august.ua.blackout.presentation.onboarding.navigation.ONBOARDING_GRAPH_ROUTE_PATTERN
import com.august.ua.blackout.presentation.onboarding.navigation.onboardingGraph
import com.august.ua.blackout.presentation.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(route = Screen.SplashScreen.route) {
            SplashScreen { screen ->
                navController.navigate(screen) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }

        onboardingGraph(navController)

        composable(route = Screen.HomeScreen.route) {
            //val selectedTab = it.arguments?.getString(HOME_SELECTED_TAB)
            HomeScreen(
                selectedTab = Screen.LocationsTabScreen.route,
                externalNavController = navController
            )
        }
    }
}