package de.mymidterm.presentation.ui.components.hobby

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import de.mymidterm.presentation.viewmodel.FootballMatchViewModel
import de.mymidterm.presentation.viewmodel.TrainingDayViewModel

@Composable
fun OverviewPage(
    navController: NavHostController,
    footballMatchViewModel: FootballMatchViewModel,
    trainingDayViewModel: TrainingDayViewModel,
    modifier: Modifier = Modifier,
) {
    val footballResults = footballMatchViewModel.footballMatches.collectAsState().value
    val trainingPlan = trainingDayViewModel.trainingDays.collectAsState().value

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Football Results:",
            fontSize = 24.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
        Box(Modifier.height(300.dp)) { // 360.dp
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = modifier.padding(8.dp)
            ) {
                items(footballResults) { match ->
                    FootballEntry(match) {
                        navController.navigate("trainingDayDetail/${match.id}")
                    }
                }
            }
        }
        AddFootballResultButton(footballMatchViewModel)

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 4.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
        )
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
}