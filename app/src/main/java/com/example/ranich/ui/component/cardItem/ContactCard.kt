package com.example.ranich.ui.component.cardItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ranich.R
import com.example.ranich.domain.model.Contact
import com.example.ranich.ui.component.card.CardType
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary
import com.example.ranich.ui.theme.Color_Secondary

@Composable
fun ContactCard(
    modifier: Modifier,
    contact: Contact,
    isCanEdit: Boolean,
    onImportanceButtonClicked: () -> Unit = {},
    onEditButtonClicked: () -> Unit = {},
) {

    var isImportanceCheck by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(all = dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier,
                imageVector = if (contact.cardType == CardType.HOSPITAL) {
                    Icons.Rounded.Add
                } else {
                    Icons.Rounded.PersonOutline
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

            Spacer(
                modifier = if (isCanEdit) {
                    modifier.width(dimensionResource(id = R.dimen.margin_padding_16))
                } else {
                    modifier.weight(1f)
                }
            )

            IconButton(
                modifier = modifier,
                onClick = {
                    isImportanceCheck = !isImportanceCheck
                    onImportanceButtonClicked()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Icon(
                    modifier = modifier,
                    imageVector = if (isImportanceCheck) {
                        Icons.Rounded.Star
                    } else {
                        Icons.Rounded.StarBorder
                    },
                    tint = if (isImportanceCheck) {
                        Color_Secondary
                    } else {
                        Color_On_Surface
                    },
                    contentDescription = null
                )
            }

            if (isCanEdit) {
                Spacer(
                    modifier = modifier.weight(1f)

                )

                TextButton(
                    onClick = { onEditButtonClicked() },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    RobotoText(
                        modifier = modifier,
                        text = stringResource(R.string.label_edit)
                    )
                }
            }
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