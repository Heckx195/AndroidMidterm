package de.mymidterm.ui.components.hobby

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.mymidterm.R
import de.mymidterm.models.TrainingDay
import de.mymidterm.ui.components.lottie.LottieGoBackButton

@Composable
fun TrainingDayDetail(
    trainingDay: TrainingDay,
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .height(56.dp)
            ) {
                LottieGoBackButton(
                    onClick = { navController.popBackStack() },
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = trainingDay.title,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            HorizontalDivider(thickness = 2.dp)
            Text(
                text = trainingDay.description,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            HorizontalDivider(thickness = 2.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gym),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Log.d("TrainingDayDetail", "Machines log: ${trainingDay.machines}")
                Column() {
                    trainingDay.machines.forEach{ machine -> (
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                            modifier = Modifier.padding(2.dp)
                        ) {
                            Text(
                                text = machine,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    )}
                }
            }
        }
    }
}