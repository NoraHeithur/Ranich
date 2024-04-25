package com.example.ranich.ui.component.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ranich.R
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier,
    isShowTabRow: Boolean = false,
    onNotificationButtonClicked: () -> Unit,
    onProfileButtonClicked: () -> Unit,
) {
    Column {
        TopAppBar(
            modifier = modifier
                .fillMaxWidth(),
            title = {
                RobotoText(
                    modifier = modifier.padding(start = dimensionResource(id = R.dimen.margin_padding_8)),
                    text = "John Doe",
                    color = Color_Primary,
                    size = dimensionResource(id = R.dimen.text_size_24).value.sp,
                    weight = FontWeight.Bold
                )
            },
            actions = {
                IconButton(
                    modifier = modifier,
                    onClick = { onNotificationButtonClicked() }
                ) {
                    Icon(
                        modifier = modifier
                            .size(dimensionResource(id = R.dimen.icon_size_small)),
                        imageVector = Icons.Outlined.Notifications,
                        tint = Color_Primary,
                        contentDescription = null
                    )
                }

                Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

                IconButton(
                    modifier = modifier,
                    onClick = { onProfileButtonClicked() }
                ) {
                    Icon(
                        modifier = modifier
                            .size(dimensionResource(id = R.dimen.icon_size_default)),
                        imageVector = Icons.Rounded.AccountCircle,
                        tint = Color_Primary,
                        contentDescription = null
                    )
                }

                Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        if (!isShowTabRow) {
            HorizontalDivider(
                modifier = modifier,
                thickness = 1.dp,
                color = Color_On_Surface
            )
        }
    }
}

@Preview
@Composable
private fun MainTopBarPreview() {
    MainTopBar(
        modifier = Modifier,
        isShowTabRow = true,
        onNotificationButtonClicked = {},
        onProfileButtonClicked = {}
    )
}