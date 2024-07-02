package com.august.ua.blackout.ui.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController

fun NavHostController.popBackStackSingle(): Boolean {
    return if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    } else {
        true
    }
}

fun NavHostController.popBackStackSingle(route: String, inclusive: Boolean = false): Boolean {
    return if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack(route = route, inclusive = inclusive)
    } else {
        true
    }
}

fun NavController.popBackStackSingle(): Boolean {
    return if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    } else {
        true
    }
}