package com.example.ranich.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ranich.ui.screen.home_graph.HomeScreen

private const val HOME_ROUTE = "home-route"

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeRoute(
    modifier: Modifier,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit = {}
) {
    navigation(
        route = AppCenterLevelRoute.HOME,
        startDestination = HOME_ROUTE,
    ) {
        composable(
            route = HOME_ROUTE,
            enterTransition = {
                NavDefaultTransition.fadeInEnter()
            },
            exitTransition = {
                NavDefaultTransition.fadeOutEnter()
            }
        ) {
            onHomeScreenTabDisplay(true)
            HomeScreen(
                modifier = modifier,
            )
        }
        nestedGraphs()
    }
}