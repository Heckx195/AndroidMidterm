package de.mymidterm.presentation.ui.components.lottie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import de.mymidterm.R

@Composable
fun LottieGoBackButton(
    onClick: () -> Unit,
    size: Dp = 70.dp,
    content: @Composable () -> Unit = {}
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.animation_go_back_button)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .size(size)
            .clickable { onClick() }
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .graphicsLayer(
                    scaleX = 4f,
                    scaleY = 4f
                )
        )
    }
    content()
}