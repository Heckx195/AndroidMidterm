package de.mymidterm

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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import de.mymidterm.models.FootballMatch
import de.mymidterm.models.TrainingDay
import de.mymidterm.ui.components.hobby.OverviewPage
import de.mymidterm.ui.theme.MyMidtermTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.mymidterm.ui.components.hobby.TrainingDayDetail

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMidtermTheme {
                MyApp()
            }
        }
    }
}

private val footballResults = listOf(
    FootballMatch(
        id = 0,
        title = "FC Bayern 1:0 Real Madrid",
        description = "09:00 Thuesday.",
        location = "München"
    ),
    FootballMatch(
        id = 1,
        title = "FC Barcelona 2:4 Arsenal",
        description = "12:00 Saturday.",
        location = "Barcelona"
    ),
    FootballMatch(
        id = 2,
        title = "Bayern Leverkusen 0:5 Dortmund",
        description = "15:30 Monday.",
        location = "Leverkusen"
    ),
    FootballMatch(
        id = 3,
        title = "Eintracht Frankfurt 2:4 Stuttgart",
        description = "17:00 Sunday.",
        location = "Frankfurt"
    ),
)

@Composable
fun MyApp() {
    val context = LocalContext.current
    val dummyMessage = context.getString(R.string.dummy_description)
    val trainingPlan = listOf(
        TrainingDay(
            id = 0,
            title = "Monday: Chest/ Shoulder/ Trizeps",
            description = dummyMessage,
            machines = listOf("Chest Press", "Scull Crush", "Dumbbells")
        ),
        TrainingDay(
            id = 1,
            title = "Tuesday: Rest-Day",
            description = dummyMessage,
            machines = listOf()
        ),
        TrainingDay(
            id = 2,
            title = "Wednesday: Back/ Bizeps/ Belly",
            description = dummyMessage,
            machines = listOf("Deadlift", "Dumbbells")

        ),
        TrainingDay(
            id = 3,
            title = "Thursday: Rest-Day",
            description = dummyMessage,
            machines = listOf()
        ),
        TrainingDay(
            id = 4,
            title = "Friday: Legs",
            description = dummyMessage,
            machines = listOf("Squats", "Leg press")
        ),
        TrainingDay(
            id = 5,
            title = "Saturday: Rest-Day",
            description = dummyMessage,
            machines = listOf("Chest Press", "Squat ")
        ),
        TrainingDay(
            id = 6,
            title = "Sunday: Endurance-Day",
            description = dummyMessage,
            machines = listOf("Jogging")
        ),
    )

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
                    OverviewPage(navController, footballResults, trainingPlan)
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
                val day = trainingPlan.find { it.id == dayId }
                day?.let { TrainingDayDetail(it, navController) }
            }
        }
    }
}
