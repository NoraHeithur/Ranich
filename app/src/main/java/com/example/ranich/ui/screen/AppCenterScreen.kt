package com.example.ranich.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ranich.ui.component.navigation.MainAppCenterNavHost
import com.example.ranich.ui.component.navigation.MainNavigationBar
import com.example.ranich.ui.component.topbar.MainTopBar
import com.example.ranich.ui.navigation.NavigationAction
import com.example.ranich.ui.navigation.navigateToThresholdScreen

@Composable
fun AppCenterScreen(
    appCenterNavController: NavHostController,
    appCenterNavigateAction: NavigationAction,
) {
    var showTabRowState by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                modifier = Modifier,
                isShowTabRow = showTabRowState,
                onNotificationButtonClicked = {

                },
                onProfileButtonClicked = {
                    appCenterNavController.navigateToThresholdScreen()
                }
            )
        },
        bottomBar = {
            MainNavigationBar(
                modifier = Modifier,
                selectedDestination = appCenterNavigateAction.currentDestination,
                navigateToTopLevelDestination = appCenterNavigateAction::navigateToAppCenterLevelDestination
            )
        }
    ) { innerPadding ->
        MainAppCenterNavHost(
            modifier = Modifier.padding(innerPadding),
            onHomeScreenTabDisplay = {
                showTabRowState = it
            },
            navController = appCenterNavController
        )
    }
}