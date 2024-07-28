package com.august.ua.blackout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.august.ua.blackout.presentation.create_update_location.CreateUpdateLocationScreen
import com.august.ua.blackout.presentation.home.HomeScreen
import com.august.ua.blackout.presentation.notification_permission.GiveNotificationPermissionScreen
import com.august.ua.blackout.presentation.onboarding.navigation.ONBOARDING_GRAPH_ROUTE_PATTERN
import com.august.ua.blackout.presentation.onboarding.navigation.onboardingGraph
import com.august.ua.blackout.presentation.splash.SplashScreen
import com.august.ua.blackout.ui.common.extensions.popBackStackSingle
import com.august.ua.blackout.ui.components.noEnterTransition
import com.august.ua.blackout.ui.components.noExitTransition

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        enterTransition = noEnterTransition,
        exitTransition = noExitTransition
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

        composable(
            route = Screen.CreateUpdateLocation.route
        ) {
            CreateUpdateLocationScreen(
                navigate = {
                    if (it == Screen.GiveNotificationPermission.route) {
                        navController.navigate(it)
                    } else {
                        navController.navigate(it) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                },
                navigateBack = {
                    navController.popBackStackSingle()
                }
            )
        }

        composable(
            route = Screen.GiveNotificationPermission.route
        ) {
            GiveNotificationPermissionScreen(
                close = {
                    navController.popBackStackSingle()
                }
            )
        }
    }
}