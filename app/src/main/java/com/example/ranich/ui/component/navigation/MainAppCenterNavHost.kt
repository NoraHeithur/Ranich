package com.example.ranich.ui.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ranich.ui.navigation.AppCenterLevelRoute
import com.example.ranich.ui.navigation.addHospitalContactRoute
import com.example.ranich.ui.navigation.addPersonalContactRoute
import com.example.ranich.ui.navigation.helpRoute
import com.example.ranich.ui.navigation.homeRoute
import com.example.ranich.ui.navigation.settingRoute
import com.example.ranich.ui.navigation.thresholdRoute

@Composable
fun MainAppCenterNavHost(
    modifier: Modifier,
    navController: NavHostController,
    onHomeScreenTabDisplay: (Boolean) -> Unit,
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = AppCenterLevelRoute.HELP,
    ) {
        homeRoute(
            modifier = modifier,
            onHomeScreenTabDisplay = {
                onHomeScreenTabDisplay(it)
            },
            nestedGraphs = {
                thresholdRoute(
                    modifier = modifier,
                    onHomeScreenTabDisplay = {
                        onHomeScreenTabDisplay(it)
                    }
                )
            }
        )

        helpRoute(
            modifier = modifier,
            onHomeScreenTabDisplay = {
                onHomeScreenTabDisplay(it)
            },
            nestedGraphs = {
                thresholdRoute(
                    modifier = modifier,
                    onHomeScreenTabDisplay = {
                        onHomeScreenTabDisplay(it)
                    }
                )
            }
        )

        settingRoute(
            modifier = modifier,
            navController = navController,
            onHomeScreenTabDisplay = {
                onHomeScreenTabDisplay(it)
            },
            nestedGraphs = {
                thresholdRoute(
                    modifier = modifier,
                    onHomeScreenTabDisplay = {
                        onHomeScreenTabDisplay(it)
                    }
                )

                addPersonalContactRoute(
                    modifier = modifier,
                    onHomeScreenTabDisplay = {
                        onHomeScreenTabDisplay(it)
                    }
                )

                addHospitalContactRoute(
                    modifier = modifier,
                    onHomeScreenTabDisplay = {
                        onHomeScreenTabDisplay(it)
                    }
                )
            }
        )
    }
}