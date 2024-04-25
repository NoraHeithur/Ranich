package com.example.ranich.ui.screen.setting_graph.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.ranich.R
import com.example.ranich.ui.component.button.CustomButton
import com.example.ranich.ui.component.card.CardElevationContainer
import com.example.ranich.ui.component.cardItem.ContactCard
import com.example.ranich.ui.screen.setting_graph.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
    modifier: Modifier,
    onCardClicked: () -> Unit = {},
    onAddPersonalButtonClicked: () -> Unit,
    onAddHospitalButtonClicked: () -> Unit,
    viewModel: SettingViewModel = koinViewModel(),
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column {
            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            Column(
                modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.margin_padding_16))
            ) {
                CustomButton(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    label = "Add Personal Contact",
                    isIconButton = true,
                    isPrimaryIcon = true,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
                ) {
                    onAddPersonalButtonClicked()
                }

                Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

                CustomButton(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth(),
                    label = "Add Hospital Contact",
                    isIconButton = true,
                    isPrimaryIcon = false,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
                ) {
                    onAddHospitalButtonClicked()
                }
            }

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
                            isCanEdit = true,
                            onImportanceButtonClicked = {}
                        )
                    }

                    Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
                }
            }
        }
    }
}