package de.mymidterm.presentation.ui.components.hobby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        FootballOverview(
            navController,
            footballMatchViewModel,
            modifier
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            thickness = 4.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 1f)
        )

        TrainingPlanOverview(
            navController,
            trainingDayViewModel,
            modifier
        )
    }
}

// Info: KISS-principle