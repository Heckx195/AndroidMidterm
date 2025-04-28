package de.mymidterm.presentation.ui.components.hobby

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import de.mymidterm.presentation.viewmodel.TrainingDayViewModel

@Composable
fun TrainingPlanOverview(
    navController: NavHostController,
    trainingDayViewModel: TrainingDayViewModel,
    modifier: Modifier = Modifier,
) {
    val trainingPlan = trainingDayViewModel.trainingDays.collectAsState().value

    Text(
        text = "Gym Training Plan:",
        fontSize = 24.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = TextStyle(
            textAlign = TextAlign.Center
        )
    )
    AddTrainingDayButton(trainingDayViewModel)
    TrainingPlan(trainingPlan, navController)
}

// Info: KISS-principle