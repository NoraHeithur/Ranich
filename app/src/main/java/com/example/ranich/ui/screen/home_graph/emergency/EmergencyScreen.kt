package com.example.ranich.ui.screen.home_graph.emergency

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ranich.R
import com.example.ranich.ui.component.card.CardElevationContainer
import com.example.ranich.ui.component.cardItem.ContactCard
import com.example.ranich.ui.screen.home_graph.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmergencyScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onCardClicked: () -> Unit = {},
) {

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(
                top = dimensionResource(id = R.dimen.margin_padding_16),
                start = dimensionResource(id = R.dimen.margin_padding_16),
                end = dimensionResource(id = R.dimen.margin_padding_16)
            )
        ) {
            items(
                items = viewModel.contactData.value.contacts,
            ) { contact ->
                CardElevationContainer(
                    modifier = modifier,
                    onCardClicked = { onCardClicked() }
                ) {
                    ContactCard(
                        modifier = modifier,
                        contact = contact,
                        isCanEdit = false,
                        onImportanceButtonClicked = {}
                    )
                }

                Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
            }
        }
    }
}

@Preview
@Composable
private fun EmergencyScreenPreview() {
    EmergencyScreen(modifier = Modifier)
}