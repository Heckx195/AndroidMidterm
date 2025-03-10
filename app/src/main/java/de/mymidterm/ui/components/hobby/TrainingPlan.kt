package de.mymidterm.ui.components.hobby

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.mymidterm.models.TrainingDay
import kotlinx.coroutines.launch

@Composable
fun TrainingPlan(
    weekdayPlan: List<TrainingDay>,
    navController: NavHostController,
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    val infiniteTransition = rememberInfiniteTransition()
    val scrollOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    LaunchedEffect(scrollOffset) {
        coroutineScope.launch {
            val maxScroll = scrollState.maxValue.toFloat()
            val targetScroll = (maxScroll * scrollOffset).toInt()
            scrollState.scrollTo(targetScroll)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(16.dp)
    ) {
        weekdayPlan.forEach{ entry -> (
            TrainingDayEntry(entry) {
                navController.navigate("trainingDayDetail/${entry.id}")
            }
        )}
    }
}