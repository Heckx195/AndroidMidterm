package de.mymidterm.ui.components.hobby

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import de.mymidterm.models.FootballMatch
import de.mymidterm.models.TrainingDay

@Composable
fun OverviewPage(
    navController: NavHostController,
    footballResults: List<FootballMatch>,
    trainingPlan: List<TrainingDay>,
    modifier: Modifier = Modifier,
) {
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
        Box(Modifier.height(360.dp)) {
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
        TrainingPlan(trainingPlan, navController)
    }
}