package com.example.ranich.ui.component.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.compose.rememberNavController
import com.example.ranich.R
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.navigation.APP_CENTER_LEVEL_DESTINATIONS
import com.example.ranich.ui.navigation.NavigationAction
import com.example.ranich.ui.navigation.isAppCenterLevelDestinationInHierarchy
import com.example.ranich.ui.navigation.topLevelNavDestination.AppCenterLevelDestinationModel
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary

@Composable
fun MainNavigationBar(
    modifier: Modifier,
    selectedDestination: NavDestination?,
    navigateToTopLevelDestination: (AppCenterLevelDestinationModel) -> Unit,
) {
    Column {
        HorizontalDivider(
            modifier = modifier,
            thickness = 1.dp,
            color = Color_On_Surface
        )

        NavigationBar(
            modifier = modifier,
            containerColor = Color.White,
            tonalElevation = 10.dp
        ) {
            APP_CENTER_LEVEL_DESTINATIONS.forEach { destination ->
                val selected = selectedDestination.isAppCenterLevelDestinationInHierarchy(destination)
                NavigationBarItem(
                    modifier = modifier,
                    icon = {
                        Icon(
                            modifier = modifier.size(dimensionResource(id = R.dimen.icon_size_small)),
                            imageVector = destination.icon,
                            contentDescription = destination.route
                        )
                    },
                    label = {
                        RobotoText(
                            modifier = modifier,
                            text = destination.topLevelName,
                            color = if (selected) Color_Primary else Color_On_Surface,
                            size = dimensionResource(id = R.dimen.text_size_12).value.sp
                        )
                    },
                    alwaysShowLabel = true,
                    selected = selected,
                    onClick = { navigateToTopLevelDestination(destination) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color_Primary,
                        selectedTextColor = Color_Primary,
                        unselectedIconColor = Color_On_Surface,
                        unselectedTextColor = Color_On_Surface,
                        indicatorColor = Color.Transparent,
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MainNavigationBarPreview() {
    val navController = rememberNavController()
    val navigateAction = remember(navController) {
        NavigationAction(navController = navController)
    }
    MainNavigationBar(
        modifier = Modifier,
        selectedDestination = navigateAction.currentDestination,
        navigateToTopLevelDestination = navigateAction::navigateToAppCenterLevelDestination
    )
}