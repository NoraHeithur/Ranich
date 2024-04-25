package com.example.ranich.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ranich.ui.screen.setting_graph.SettingScreen

private const val SETTING_ROUTE = "setting-route"
private const val CREATE_PERSONAL_CONTACT_ROUTE =
    "${AppCenterLevelRoute.SETTING}/addPersonalContact"
private const val CREATE_HOSPITAL_CONTACT_ROUTE =
    "${AppCenterLevelRoute.SETTING}/addHospitalContact"

fun NavController.navigateToSettingScreen(navOptions: NavOptions? = null) {
    this.navigate(SETTING_ROUTE, navOptions)
}

fun NavController.navigateToAddPersonalContactScreen() {
    this.navigate(CREATE_PERSONAL_CONTACT_ROUTE)
}

fun NavController.navigateToAddHospitalContactScreen() {
    this.navigate(CREATE_HOSPITAL_CONTACT_ROUTE)
}

fun NavGraphBuilder.settingRoute(
    modifier: Modifier,
    navController: NavController,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit = {},
) {
    navigation(
        route = AppCenterLevelRoute.SETTING,
        startDestination = SETTING_ROUTE,
    ) {
        composable(
            route = SETTING_ROUTE,
            enterTransition = {
                NavDefaultTransition.fadeInEnter()
            },
            exitTransition = {
                NavDefaultTransition.fadeOutEnter()
            }
        ) {
            onHomeScreenTabDisplay(true)
            SettingScreen(
                modifier = modifier,
                onAddPersonalButtonClicked = {
                    navController.navigateToAddPersonalContactScreen()
                },
                onAddHospitalButtonClicked = {
                    navController.navigateToAddHospitalContactScreen()
                },
            )
        }
        nestedGraphs()
    }
}

fun NavGraphBuilder.addPersonalContactRoute(
    modifier: Modifier,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
) {
    composable(
        route = CREATE_PERSONAL_CONTACT_ROUTE,
        enterTransition = {
            NavDefaultTransition.fadeInEnter()
        },
        exitTransition = {
            NavDefaultTransition.fadeOutEnter()
        }
    ) {
        onHomeScreenTabDisplay(false)

    }
}

fun NavGraphBuilder.addHospitalContactRoute(
    modifier: Modifier,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
) {
    composable(
        route = CREATE_HOSPITAL_CONTACT_ROUTE,
        enterTransition = {
            NavDefaultTransition.fadeInEnter()
        },
        exitTransition = {
            NavDefaultTransition.fadeOutEnter()
        }
    ) {
        onHomeScreenTabDisplay(false)

    }
}