package com.august.ua.blackout.presentation.home

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
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.august.ua.blackout.navigation.currentDestinationObserved
import com.august.ua.blackout.navigation.isTopLevelDestinationInHierarchy
import com.august.ua.blackout.presentation.home.navigation.BottomNavigationItems
import com.august.ua.blackout.presentation.home.navigation.HOME_GRAPH_ROUTE_PATTERN
import com.august.ua.blackout.presentation.home.navigation.homeGraph

@Composable
fun HomeScreen(
    selectedTab: String,
    externalNavController: NavController,
) {
    var tab by rememberSaveable {
        mutableStateOf(selectedTab)
    }
    val bottomNavigationItems = BottomNavigationItems.entries
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                containerColor = Color.Transparent
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
                                style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 0.sp)
                            )
                        },
                        icon = {
//                            BadgedBox(badge = {
//                                if (item.hasNews && notificationsCount > 0) {
//                                    Badge()
//                                }
//                            }) {
//                                Icon(painterResource(id = item.icon), contentDescription = null)
//                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painterResource(id = R.drawable.bg_home),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = padding.calculateBottomPadding())
            ) {
                NavHost(
                    navController = navController,
                    startDestination = HOME_GRAPH_ROUTE_PATTERN,
                ) {
                    homeGraph(navController, externalNavController = externalNavController)
                }
            }
        }
    }

//    LaunchedEffect(tab) {
//        if (tab != Screen.CalendarTabScreen.route) {
//            navigate(route = tab, navController = navController)
//            tab = Screen.CalendarTabScreen.route
//        }
//    }
    LaunchedEffect(tab) {
        if (tab != HOME_GRAPH_ROUTE_PATTERN) {
            navigate(route = tab, navController = navController)
            tab = HOME_GRAPH_ROUTE_PATTERN
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