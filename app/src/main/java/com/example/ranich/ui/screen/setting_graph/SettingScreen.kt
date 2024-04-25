@file:OptIn(ExperimentalFoundationApi::class)

package com.example.ranich.ui.screen.setting_graph

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ranich.R
import com.example.ranich.ui.component.tab.ContentTabRow
import com.example.ranich.ui.component.tab.SettingTabRowContent

@Composable
fun SettingScreen(
    modifier: Modifier,
    onAddPersonalButtonClicked: () -> Unit,
    onAddHospitalButtonClicked: () -> Unit,
) {
    SettingContainer(modifier = modifier) {
        SettingContent(
            modifier = Modifier,
            onAddPersonalButtonClicked = onAddPersonalButtonClicked,
            onAddHospitalButtonClicked = onAddHospitalButtonClicked,
            state = it
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SettingContainer(
    modifier: Modifier,
    content: @Composable (PagerState) -> Unit,
) {
    Surface(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        val tabList = listOf(
            stringResource(id = R.string.label_information),
            stringResource(id = R.string.label_contact),
        )

        val selectedState = rememberPagerState(
            pageCount = { tabList.size }
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ContentTabRow(
                modifier = Modifier,
                tabList = tabList,
                state = selectedState
            )
            content(selectedState)
        }
    }
}

@Composable
private fun SettingContent(
    modifier: Modifier,
    onAddPersonalButtonClicked: () -> Unit,
    onAddHospitalButtonClicked: () -> Unit,
    state: PagerState
) {
    SettingTabRowContent(
        modifier = modifier,
        onAddPersonalButtonClicked = onAddPersonalButtonClicked,
        onAddHospitalButtonClicked = onAddHospitalButtonClicked,
        state = state
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        modifier = Modifier,
        {},
        {}
    )
}