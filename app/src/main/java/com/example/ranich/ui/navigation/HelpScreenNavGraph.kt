package com.example.ranich.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ranich.ui.screen.help_graph.HelpScreen

private const val HELP_ROUTE = "help-route"

fun NavController.navigateToHelpScreen(navOptions: NavOptions? = null) {
    this.navigate(HELP_ROUTE, navOptions)
}

fun NavGraphBuilder.helpRoute(
    modifier: Modifier,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit = {}
) {
    navigation(
        route = AppCenterLevelRoute.HELP,
        startDestination = HELP_ROUTE,
    ) {
        composable(
            route = HELP_ROUTE,
            enterTransition = {
                NavDefaultTransition.fadeInEnter()
            },
            exitTransition = {
                NavDefaultTransition.fadeOutEnter()
            }
        ) {
            onHomeScreenTabDisplay(false)
            HelpScreen(
                modifier
            )
        }
        nestedGraphs()
    }
}