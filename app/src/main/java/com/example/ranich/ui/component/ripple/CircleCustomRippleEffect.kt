package com.example.ranich.ui.component.ripple

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ranich.ui.theme.Color_Secondary_50

@Composable
fun CircleCustomRippleEffect(
    modifier: Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "transitionRipple")
    val animation = infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600)
        ),
        label = "floatAnimate"
    )

    Box(
        modifier = modifier
            .scale(animation.value)
            .clip(shape = CircleShape)
            .size(280.dp)
            .background(Color_Secondary_50)
    )
}

@Preview
@Composable
private fun CustomRippleEffectPreview() {
    CircleCustomRippleEffect(Modifier)
}