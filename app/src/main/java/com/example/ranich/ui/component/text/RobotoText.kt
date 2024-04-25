package com.example.ranich.ui.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ranich.R
import com.example.ranich.ui.theme.Color_Primary

@Composable
fun RobotoText(
    modifier: Modifier,
    text: String,
    color: Color = Color_Primary,
    size: TextUnit = dimensionResource(id = R.dimen.text_size_16).value.sp,
    weight: FontWeight = FontWeight.Normal,
    style: FontStyle = FontStyle.Normal,
    align: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    lineHeight: TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = size,
        fontStyle = style,
        fontWeight = weight,
        textAlign = align,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        lineHeight = lineHeight
    )
}