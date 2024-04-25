package com.example.ranich.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ranich.ui.navigation.MainTopLevelRoute
import com.example.ranich.ui.navigation.NavDefaultTransition
import com.example.ranich.ui.navigation.NavigationAction
import com.example.ranich.ui.screen.AppCenterScreen
import com.example.ranich.ui.screen.login.LogInScreen

@Composable
fun RanichMainScreen(
    userSession: String
) {
    MainScreen(userSession)
}

@Composable
fun MainScreen(
    userSession: String
) {

    val mainNavController = rememberNavController()
    val appCenterNavController = rememberNavController()
    val appCenterNavigateAction = remember(appCenterNavController) {
        NavigationAction(navController = appCenterNavController)
    }

    NavHost(
        navController = mainNavController,
        startDestination = if (userSession.isNotEmpty()) MainTopLevelRoute.CENTER else MainTopLevelRoute.LOGIN
    ) {
        composable(
            route = MainTopLevelRoute.LOGIN,
            enterTransition = {
                NavDefaultTransition.fadeInEnter()
            },
            exitTransition = {
                NavDefaultTransition.fadeOutEnter()
            }
        ) {
            LogInScreen(navController = mainNavController)
        }
        composable(
            route = MainTopLevelRoute.CENTER,
            enterTransition = {
                NavDefaultTransition.fadeInEnter()
            },
            exitTransition = {
                NavDefaultTransition.fadeOutEnter()
            }
        ) {
            AppCenterScreen(
                appCenterNavController,
                appCenterNavigateAction,
            )
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
private fun RanichApplicationPreview() {
    MainScreen("")
}