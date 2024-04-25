package com.example.ranich.ui.component.cardItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ranich.R
import com.example.ranich.domain.model.Contact
import com.example.ranich.ui.component.card.CardType
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary

@Composable
fun PersonalCard(
    modifier: Modifier,
    contact: Contact,
) {
    Column(
        modifier = modifier.padding(all = dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier,
                imageVector = if (contact.cardType == CardType.ADDRESS) {
                    Icons.Outlined.LocationOn
                } else {
                    Icons.Outlined.Phone
                },
                tint = Color_On_Surface,
                contentDescription = null
            )

            Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.margin_padding_8)))

            RobotoText(
                modifier = modifier,
                text = contact.cardType.toString(),
                color = Color_On_Surface
            )
        }

        Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.margin_padding_8)))

        RobotoText(
            modifier = modifier
                .padding(
                    start = dimensionResource(id = R.dimen.margin_padding_32),
                    top = dimensionResource(id = R.dimen.margin_padding_8)
                ),
            text = if (contact.cardType == CardType.ADDRESS) {
                contact.name
            } else {
                contact.contactNumber
            },
            color = Color_Primary,
            weight = FontWeight.Bold
        )

        if (contact.cardType == CardType.ADDRESS) {
            RobotoText(
                modifier = modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.margin_padding_32),
                        top = dimensionResource(id = R.dimen.margin_padding_8)
                    ),
                text = contact.relationship,
                color = Color_On_Surface,
                maxLines = Int.MAX_VALUE
            )
        }
    }
}