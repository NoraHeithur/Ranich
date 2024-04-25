package com.example.ranich.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ranich.ui.screen.special.ThresholdManager

private const val THRESHOLD_ROUTE = "threshold-route"

fun NavController.navigateToThresholdScreen() {
    this.navigate(THRESHOLD_ROUTE)
}

fun NavGraphBuilder.thresholdRoute(
    modifier: Modifier,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
) {
    navigation(
        route = "main/${AppCenterLevelRoute.THRESHOLD}",
        startDestination = THRESHOLD_ROUTE,
    ) {
        composable(
            route = THRESHOLD_ROUTE,
            enterTransition = {
                NavDefaultTransition.fadeInEnter()
            },
            exitTransition = {
                NavDefaultTransition.fadeOutEnter()
            }
        ) {
            onHomeScreenTabDisplay(false)
            ThresholdManager(modifier)
        }
    }
}