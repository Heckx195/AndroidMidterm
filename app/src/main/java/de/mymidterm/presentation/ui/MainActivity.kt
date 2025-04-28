package de.mymidterm.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import de.mymidterm.presentation.ui.components.hobby.OverviewPage
import de.mymidterm.presentation.ui.theme.MyMidtermTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mymidterm.presentation.ui.components.hobby.TrainingDayDetail
import de.mymidterm.presentation.viewmodel.FootballMatchViewModel
import de.mymidterm.presentation.viewmodel.TrainingDayViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val footballMatchViewModel: FootballMatchViewModel by viewModel()
    private val trainingDayViewModel: TrainingDayViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyMidtermTheme {
                MyApp(footballMatchViewModel, trainingDayViewModel)
            }
        }
    }
}

@Composable
fun MyApp(
    footballMatchViewModel: FootballMatchViewModel,
    trainingDayViewModel: TrainingDayViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val navController = rememberNavController()
        NavHost(
            navController, startDestination = "overviewPage",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable("overviewPage") {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    OverviewPage(navController, footballMatchViewModel, trainingDayViewModel)
                }
            }
            composable(
                "trainingDayDetail/{dayId}",
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideIntoContainer(
                        animationSpec = tween(300, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(
                            300, easing = LinearEasing
                        )
                    ) + slideOutOfContainer(
                        animationSpec = tween(300, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                }
            ) { backStackEntry ->
                val dayId = backStackEntry.arguments?.getString("dayId")?.toInt()
                val trainingPlan = trainingDayViewModel.trainingDays.collectAsState().value
                val day = trainingPlan.find { it.id == dayId }
                day?.let { TrainingDayDetail(it, navController) }
            }
        }
    }
}

/*
private val footballResults = listOf(
    FootballMatchEntity(
        id = 0,
        title = "FC Bayern 1:0 Real Madrid",
        description = "09:00 Thuesday.",
        location = "München"
    ),
    FootballMatchEntity(
        id = 1,
        title = "FC Barcelona 2:4 Arsenal",
        description = "12:00 Saturday.",
        location = "Barcelona"
    ),
    FootballMatchEntity(
        id = 2,
        title = "Bayern Leverkusen 0:5 Dortmund",
        description = "15:30 Monday.",
        location = "Leverkusen"
    ),
    FootballMatchEntity(
        id = 3,
        title = "Eintracht Frankfurt 2:4 Stuttgart",
        description = "17:00 Sunday.",
        location = "Frankfurt"
    ),
)
 */

/*
val context = LocalContext.current
    val dummyMessage = context.getString(R.string.dummy_description)
    val trainingPlan = listOf(
        TrainingDayEntity(
            id = 0,
            title = "Monday: Chest/ Shoulder/ Trizeps",
            description = dummyMessage,
            machines = listOf("Chest Press", "Scull Crush", "Dumbbells")
        ),
        TrainingDayEntity(
            id = 1,
            title = "Tuesday: Rest-Day",
            description = dummyMessage,
            machines = listOf()
        ),
        TrainingDayEntity(
            id = 2,
            title = "Wednesday: Back/ Bizeps/ Belly",
            description = dummyMessage,
            machines = listOf("Deadlift", "Dumbbells")

        ),
        TrainingDayEntity(
            id = 3,
            title = "Thursday: Rest-Day",
            description = dummyMessage,
            machines = listOf()
        ),
        TrainingDayEntity(
            id = 4,
            title = "Friday: Legs",
            description = dummyMessage,
            machines = listOf("Squats", "Leg press")
        ),
        TrainingDayEntity(
            id = 5,
            title = "Saturday: Rest-Day",
            description = dummyMessage,
            machines = listOf("Chest Press", "Squat ")
        ),
        TrainingDayEntity(
            id = 6,
            title = "Sunday: Endurance-Day",
            description = dummyMessage,
            machines = listOf("Jogging")
        ),
    )
 */