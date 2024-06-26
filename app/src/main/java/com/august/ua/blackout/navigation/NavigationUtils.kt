package com.august.ua.blackout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

val NavController.currentDestinationObserved: NavDestination?
    @Composable get() = currentBackStackEntryAsState().value?.destination

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: String) =
    this?.hierarchy?.any {
        it.route?.contains(destination, true) ?: false
    } ?: false