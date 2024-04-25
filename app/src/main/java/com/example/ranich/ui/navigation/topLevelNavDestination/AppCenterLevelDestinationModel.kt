package com.example.ranich.ui.navigation.topLevelNavDestination

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ranich.ui.navigation.AppCenterLevelRoute

enum class AppCenterLevelDestinationModel(
    val route: String,
    val icon: ImageVector,
    val topLevelName: String,
) {
    HOME(
        route = AppCenterLevelRoute.HOME,
        icon = Icons.Outlined.Home,
        topLevelName = "Home",
    ),
    HELP(
        route = AppCenterLevelRoute.HELP,
        icon = Icons.Rounded.Sensors,
        topLevelName = "Need Help",
    ),
    SETTING(
        route = AppCenterLevelRoute.SETTING,
        icon = Icons.AutoMirrored.Rounded.List,
        topLevelName = "Setting",
    ),
    THRESHOLD(
        route = AppCenterLevelRoute.THRESHOLD,
        icon = Icons.AutoMirrored.Rounded.List,
        topLevelName = "Threshold",
    ),
}