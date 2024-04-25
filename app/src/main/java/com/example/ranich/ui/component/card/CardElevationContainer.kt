package com.example.ranich.ui.component.card

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ranich.R
import com.example.ranich.ui.theme.Color_Surface

@Composable
fun CardElevationContainer(
    modifier: Modifier,
    onCardClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        onClick = { onCardClicked() },
        shape = RoundedCornerShape(
            dimensionResource(id = R.dimen.margin_padding_16)
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation_4),
            pressedElevation = dimensionResource(id = R.dimen.elevation_4),
            focusedElevation = dimensionResource(id = R.dimen.elevation_4),
            hoveredElevation = dimensionResource(id = R.dimen.elevation_4)
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color_Surface
        )
    ) {
        content()
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun CardElevationContainerPreview() {
    CardElevationContainer(
        modifier = Modifier,
        onCardClicked = {}
    ) {

    }
}

enum class CardType {
    EMERGENCY,
    HOSPITAL,
    ADDRESS,
    MOBILE,
    HELP,
    FALLING;

    override fun toString(): String {
        return name.lowercase().replaceFirstChar { it.uppercase() }
    }
}