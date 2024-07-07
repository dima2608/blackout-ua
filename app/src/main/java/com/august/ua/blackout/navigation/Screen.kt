package com.august.ua.blackout.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class Screen(val route: String) {

    data object SplashScreen : Screen("splash_screen")
    data object HomeScreen: Screen("home_screen")
    data object OnboardingScreen: Screen("onboarding_screen")
    data object SearchScreen: Screen("search_screen")
    data object CalendarTabScreen: Screen("calendar_screen")
    data object SettingsTabScreen: Screen("settings_screen")
    data object LocationsTabScreen: Screen("locations_screen")
    data object CreateUpdateLocation: Screen("create_update_location_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsNullable(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun createArgsRoute(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/{$args}")
            }
        }
    }
    fun createNullableArgsRoute(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("?$args={$args}")
            }
        }
    }

    @SuppressLint("RestrictedApi")
    fun NavController.navigateWithParam(
        route: String,
        data: Parcelable,
        key: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        try {
            val routeLink = NavDeepLinkRequest
                .Builder
                .fromUri(NavDestination.createRoute(route).toUri())
                .build()

            val bundle = Bundle()
            bundle.putParcelable(key, data)

            val deepLinkMatch = graph.matchDeepLink(routeLink)
            if (deepLinkMatch != null) {
                val destination = deepLinkMatch.destination
                val id = destination.id
                navigate(id, bundle, navOptions, navigatorExtras)
            } else {
                navigate(route, navOptions, navigatorExtras)
            }
        } catch (e: IllegalArgumentException) {
            Log.e("NavigationError", "-----> ${e.message}")

        }
    }

    @SuppressLint("RestrictedApi")
    fun NavController.navigateWithParam(
        route: String,
        data: Array<Parcelable>,
        key: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val routeLink = NavDeepLinkRequest
            .Builder
            .fromUri(NavDestination.createRoute(route).toUri())
            .build()

        val bundle = Bundle()
        bundle.putParcelableArray(key, data)

        val deepLinkMatch = graph.matchDeepLink(routeLink)
        if (deepLinkMatch != null) {
            val destination = deepLinkMatch.destination
            val id = destination.id
            navigate(id, bundle, navOptions, navigatorExtras)
        } else {
            navigate(route, navOptions, navigatorExtras)
        }
    }
}