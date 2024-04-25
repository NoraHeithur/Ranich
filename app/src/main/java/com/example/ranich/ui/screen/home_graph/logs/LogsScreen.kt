package com.example.ranich.ui.screen.home_graph.logs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ranich.R
import com.example.ranich.ui.component.button.CustomButton
import com.example.ranich.ui.component.button.IconPosition
import com.example.ranich.ui.component.card.CardElevationContainer
import com.example.ranich.ui.component.cardItem.LogCard
import com.example.ranich.ui.screen.home_graph.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogsScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onCardClicked: () -> Unit = {},
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column {

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            Column(
                modifier = modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.margin_padding_16))
                    .fillMaxWidth()
            ) {
                CustomButton(
                    modifier = Modifier
                        .align(alignment = Alignment.End),
                    label = stringResource(id = R.string.label_button_clear_log),
                    isIconButton = true,
                    iconPosition = IconPosition.END,
                    onButtonClicked = {

                    }
                )
            }

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            LazyColumn(
                modifier = modifier
                    .fillMaxHeight(),
                contentPadding = PaddingValues(
                    start = dimensionResource(id = R.dimen.margin_padding_16),
                    end = dimensionResource(id = R.dimen.margin_padding_16)
                )
            ) {
                items(
                    items = viewModel.logs.value.contacts,
                ) { contact ->
                    CardElevationContainer(
                        modifier = modifier,
                        onCardClicked = { onCardClicked() }
                    ) {
                        LogCard(
                            modifier = modifier,
                            contact = contact,
                        )
                    }

                    Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
                }
            }
        }
    }
}

@Preview
@Composable
private fun LogsScreenPreview() {
    LogsScreen(modifier = Modifier)
}