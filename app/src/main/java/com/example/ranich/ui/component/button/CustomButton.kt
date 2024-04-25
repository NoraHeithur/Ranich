package com.example.ranich.ui.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ranich.R
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_Light_Gray
import com.example.ranich.ui.theme.Color_On_Surface
import com.example.ranich.ui.theme.Color_Primary

@Composable
fun CustomButton(
    modifier: Modifier,
    label: String,
    isIconButton: Boolean = false,
    isPrimaryIcon: Boolean = true,
    isEnabled: Boolean = true,
    shape: Shape = RoundedCornerShape(percent = 50),
    iconPosition: IconPosition = IconPosition.START,
    onButtonClicked: () -> Unit,
) {
    ElevatedButton(
        modifier = modifier,
        onClick = { onButtonClicked() },
        colors = if (isPrimaryIcon) {
            ButtonDefaults.buttonColors(
                containerColor = Color_Primary,
                disabledContainerColor = Color_On_Surface
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = Color_Light_Gray
            )
        },
        enabled = isEnabled,
        shape = shape,
        elevation = null
    ) {

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            if (isIconButton && iconPosition == IconPosition.START) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(dimensionResource(id = R.dimen.margin_padding_24)),
                    imageVector = Icons.Rounded.Add,
                    tint = if (isPrimaryIcon) Color.White else Color_Primary,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))
            }

            RobotoText(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = label,
                color = if (isPrimaryIcon) Color.White else Color_Primary,
                size = dimensionResource(id = R.dimen.text_size_14).value.sp,
                weight = FontWeight.SemiBold
            )


            if (isIconButton && iconPosition == IconPosition.END) {
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(dimensionResource(id = R.dimen.margin_padding_24)),
                    imageVector = Icons.Rounded.Close,
                    tint = if (isPrimaryIcon) Color.White else Color_Primary,
                    contentDescription = null
                )
            }
        }
    }
}

enum class IconPosition {
    START,
    END
}

@Preview
@Composable
private fun CustomButtonPreview() {
    CustomButton(
        modifier = Modifier,
        label = "Button",
        isIconButton = true,
        iconPosition = IconPosition.END,
        onButtonClicked = {}
    )
}