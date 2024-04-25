package com.example.ranich.ui.screen.home_graph.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ranich.R
import com.example.ranich.domain.model.Contact
import com.example.ranich.domain.model.UserProfile
import com.example.ranich.ui.component.card.CardElevationContainer
import com.example.ranich.ui.component.card.CardType
import com.example.ranich.ui.component.cardItem.PersonalCard
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.screen.home_graph.HomeViewModel
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary
import org.koin.androidx.compose.koinViewModel

@Composable
fun PersonalScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {

        var info: UserProfile? by remember { mutableStateOf(null) }
        var treatmentFiled by remember { mutableStateOf(TextFieldValue("")) }

        viewModel.getUserInfo {
            info = it
            treatmentFiled = TextFieldValue(text = it.treatmentHistory)
        }

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = dimensionResource(id = R.dimen.margin_padding_16)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            Icon(
                modifier = modifier.size(120.dp),
                imageVector = Icons.Rounded.AccountCircle,
                tint = Color_Primary,
                contentDescription = null
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

            RobotoText(
                modifier = modifier,
                text = "${info?.fullName} (${info?.age})",
                color = Color_Primary,
                size = dimensionResource(id = R.dimen.text_size_24).value.sp,
                weight = FontWeight.Bold
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

            RobotoText(
                modifier = modifier,
                text = "PID: ${info?.id}",
                color = Color_Primary,
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            CardElevationContainer(
                modifier = modifier,
                onCardClicked = {}
            ) {
                PersonalCard(
                    modifier = modifier,
                    contact = Contact(
                        contactId = info?.id ?: "",
                        name =info?.addressModel?.name ?: "",
                        relationship = info?.addressModel?.details ?: "",
                        contactNumber = info?.addressModel?.zipCode ?: "",
                        isEmergency = false,
                        cardType = CardType.ADDRESS
                    ),
                )
            }

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            CardElevationContainer(
                modifier = modifier,
                onCardClicked = {}
            ) {
                PersonalCard(
                    modifier = modifier,
                    contact = Contact(
                        contactId = info?.id ?: "",
                        name =info?.fullName ?: "",
                        relationship = "",
                        contactNumber = info?.phoneNumber ?: "",
                        isEmergency = false,
                        cardType = CardType.MOBILE
                    ),
                )
            }

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier
                        .size(size = dimensionResource(id = R.dimen.icon_size_small)),
                    imageVector = Icons.Outlined.LocalHospital,
                    tint = Color_Primary,
                    contentDescription = null
                )

                Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

                RobotoText(
                    modifier = modifier,
                    text = "Treatment history",
                    color = Color_On_Surface
                )
            }

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = treatmentFiled,
                onValueChange = {
                    treatmentFiled = it
                },
                readOnly = true,
                singleLine = false,
                minLines = dimensionResource(id = R.dimen.margin_padding_8).value.toInt(),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color_Primary,
                    unfocusedBorderColor = Color_Primary,
                    focusedTextColor = Color_Primary,
                    unfocusedTextColor = Color_Primary,
                )
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
        }
    }
}

@Preview
@Composable
private fun PersonalScreenPreview() {
    PersonalScreen(
        modifier = Modifier
    )
}