package com.august.ua.blackout.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.august.ua.blackout.R
import com.august.ua.blackout.navigation.Screen
import com.august.ua.blackout.navigation.currentDestinationObserved
import com.august.ua.blackout.navigation.isTopLevelDestinationInHierarchy
import com.august.ua.blackout.presentation.home.navigation.BottomNavigationItems
import com.august.ua.blackout.presentation.home.navigation.LOCATIONS_GRAPH_ROUTE_PATTERN
import com.august.ua.blackout.presentation.home.navigation.calendarGraph
import com.august.ua.blackout.presentation.home.navigation.locationsGraph
import com.august.ua.blackout.presentation.home.navigation.settingsGraph
import com.august.ua.blackout.ui.theme.Black
import com.august.ua.blackout.ui.theme.BlackoutTextStyle
import com.august.ua.blackout.ui.theme.Transparent
import com.august.ua.blackout.ui.theme.White
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    selectedTab: String,
    externalNavController: NavController,
) {
    var tab by rememberSaveable {
        mutableStateOf(selectedTab)
    }

    val bottomNavigationItems = BottomNavigationItems.entries
    val navController = rememberNavController()
    //val notificationsCount by viewModel.notificationsCount.collectAsStateWithLifecycle(initialValue = 0)
    val notificationsCount = 0


    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.background(Black),
                containerColor = Transparent,
                contentColor = Transparent
            ) {
                bottomNavigationItems.forEach { item ->
                    val selected = navController.currentDestinationObserved.isTopLevelDestinationInHierarchy(item.route)
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navigate(route = item.route, navController = navController)
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.title),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = BlackoutTextStyle.t4TextSmallDescription,
                                color = White
                            )
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (item.hasNews && notificationsCount > 0) {
                                    Badge()
                                }
                            }) {
                                val icon = if(selected) item.iconFill else item.icon
                                Icon(imageVector = icon, contentDescription = null)
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Transparent,
                            selectedIconColor = White,
                            selectedTextColor = White,
                            unselectedIconColor = White,
                            unselectedTextColor = White
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.
                fillMaxSize(),
                painter = painterResource(id = R.drawable.bg_blue_yellow_gradient),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = padding.calculateBottomPadding())
            ) {
                NavHost(
                    navController = navController,
                    startDestination = LOCATIONS_GRAPH_ROUTE_PATTERN,
                ) {
                    locationsGraph(navController = navController, externalNavController = externalNavController)
                    calendarGraph(navController = navController, externalNavController = externalNavController)
                    settingsGraph(navController = navController, externalNavController = externalNavController)
                }
            }
        }
    }

    LaunchedEffect(tab) {
        if (tab != Screen.LocationsTabScreen.route) {
            navigate(route = tab, navController = navController)
            tab = Screen.LocationsTabScreen.route
        }
    }
}

private fun navigate(route: String, navController: NavController) {
    val navOptions: NavOptions = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    navController.navigate(route, navOptions)
}