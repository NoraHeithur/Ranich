package com.example.ranich.ui.screen.home_graph

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
import com.example.ranich.ui.component.tab.HomeTabRowContent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
) {
    HomeScreenContainer(
        modifier = modifier
    ) {
        HomeScreenContent(
            modifier = Modifier,
            state = it
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContainer(
    modifier: Modifier,
    content: @Composable (PagerState) -> Unit
) {
    Surface(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        val tabList = listOf(
            stringResource(id = R.string.label_personal),
            stringResource(id = R.string.label_emergency),
            stringResource(id = R.string.label_logs)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    state: PagerState
) {
    HomeTabRowContent(modifier = modifier, state = state)
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenContainer(modifier = Modifier) {
        HomeScreenContent(modifier = Modifier, state = it)
    }
}