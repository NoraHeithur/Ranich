package com.example.ranich.ui.navigation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.example.ranich.ui.navigation.topLevelNavDestination.AppCenterLevelDestinationModel

class NavigationAction(private val navController: NavController) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToAppCenterLevelDestination(destination: AppCenterLevelDestinationModel) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (destination) {
            AppCenterLevelDestinationModel.HOME -> navController.navigateToHomeScreen(topLevelNavOptions)
            AppCenterLevelDestinationModel.HELP -> navController.navigateToHelpScreen(topLevelNavOptions)
            AppCenterLevelDestinationModel.SETTING -> navController.navigateToSettingScreen(topLevelNavOptions)
            else -> {}
        }
    }
}

fun NavDestination?.isAppCenterLevelDestinationInHierarchy(destination: AppCenterLevelDestinationModel) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

object AppCenterLevelRoute {
    const val HOME = "Home-Graph"
    const val HELP = "Help-Graph"
    const val SETTING = "Setting-Graph"
    const val THRESHOLD = "Threshold-Graph"
}

object MainTopLevelRoute {
    const val LOGIN = "LogIn"
    const val CENTER = "Center"
}

object NavDefaultTransition {
    fun fadeInEnter(durationMillis: Int = 300) = fadeIn(
        animationSpec = tween(
            durationMillis, easing = FastOutLinearInEasing
        )
    )
    fun fadeOutEnter(durationMillis: Int = 300) = fadeOut(
        animationSpec = tween(
            durationMillis, easing = FastOutLinearInEasing
        )
    )
}

val APP_CENTER_LEVEL_DESTINATIONS: List<AppCenterLevelDestinationModel> = AppCenterLevelDestinationModel.entries.take(3)