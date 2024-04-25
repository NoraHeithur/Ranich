package com.example.ranich.ui.component.cardItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CrisisAlert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ranich.R
import com.example.ranich.domain.model.Contact
import com.example.ranich.ui.component.card.CardType
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary

@Composable
fun LogCard(
    modifier: Modifier,
    contact: Contact,
) {
    Column(
        modifier = modifier
            .padding(all = dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier,
                imageVector = if (contact.cardType == CardType.HELP) {
                    Icons.Outlined.Notifications
                } else {
                    Icons.Outlined.CrisisAlert
                },
                tint = Color_On_Surface,
                contentDescription = null
            )

            Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.margin_padding_8)))

            RobotoText(
                modifier = modifier,
                text = if (contact.cardType == CardType.HELP) {
                    stringResource(id = R.string.label_need_help_alert)
                } else {
                    stringResource(id = R.string.label_falling_detect_alert)
                },
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
            text = contact.name,
            color = Color_Primary,
            weight = FontWeight.Bold
        )

        Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.margin_padding_8)))

        RobotoText(
            modifier = modifier
                .padding(
                    start = dimensionResource(id = R.dimen.margin_padding_32),
                    top = dimensionResource(id = R.dimen.margin_padding_8)
                ),
            text = contact.relationship,
            color = Color_On_Surface,
        )

        Spacer(modifier = modifier.width(dimensionResource(id = R.dimen.margin_padding_8)))

        if (contact.cardType == CardType.FALLING) {
            RobotoText(
                modifier = modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.margin_padding_32),
                        top = dimensionResource(id = R.dimen.margin_padding_8)
                    ),
                text = contact.contactNumber,
                color = Color_On_Surface,
            )
        }
    }
}