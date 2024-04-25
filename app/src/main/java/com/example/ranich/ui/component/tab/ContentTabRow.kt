package com.example.ranich.ui.component.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ranich.R
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.screen.home_graph.emergency.EmergencyScreen
import com.example.ranich.ui.screen.home_graph.logs.LogsScreen
import com.example.ranich.ui.screen.home_graph.personal.PersonalScreen
import com.example.ranich.ui.screen.setting_graph.contact.ContactScreen
import com.example.ranich.ui.screen.setting_graph.information.InformationScreen
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentTabRow(
    modifier: Modifier,
    tabList: List<String>,
    state: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier
            .fillMaxWidth(),
        selectedTabIndex = state.currentPage,
        containerColor = Color.White,
        indicator = { position ->
            TabRowDefaults.SecondaryIndicator(
                modifier = modifier.tabIndicatorOffset(position[state.currentPage]),
                color = Color_Primary
            )
        },
        divider = {
            HorizontalDivider(
                modifier = modifier,
                thickness = 1.dp,
                color = Color_On_Surface
            )
        }
    ) {
        tabList.forEachIndexed { index, tab ->
            Tab(
                selected = state.currentPage == index,
                selectedContentColor = Color_On_Surface,
                onClick = {
                    coroutineScope.launch {
                        state.animateScrollToPage(index)
                    }
                }
            ) {
                RobotoText(
                    modifier = modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.margin_padding_16),
                            bottom = dimensionResource(id = R.dimen.margin_padding_16)
                        ),
                    text = tab,
                    color = if (state.currentPage == index) {
                        Color_Primary
                    } else {
                        Color_On_Surface
                    },
                    weight = if (state.currentPage == index) {
                        FontWeight.SemiBold
                    } else {
                        FontWeight.Normal
                    },
                    align = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabRowContent(
    modifier: Modifier,
    state: PagerState,
) {
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = state
    ) { index ->
        when (index) {
            0 -> PersonalScreen(modifier = Modifier)
            1 -> EmergencyScreen(modifier = Modifier)
            2 -> LogsScreen(modifier = Modifier)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingTabRowContent(
    modifier: Modifier,
    onAddPersonalButtonClicked: () -> Unit,
    onAddHospitalButtonClicked: () -> Unit,
    state: PagerState,
) {
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = state
    ) { index ->
        when (index) {
            0 -> InformationScreen(modifier = Modifier)
            1 -> ContactScreen(
                modifier = Modifier,
                onAddPersonalButtonClicked = onAddPersonalButtonClicked,
                onAddHospitalButtonClicked = onAddHospitalButtonClicked,
            )
        }
    }
}