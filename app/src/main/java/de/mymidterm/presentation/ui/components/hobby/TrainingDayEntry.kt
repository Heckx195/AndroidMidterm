package de.mymidterm.presentation.ui.components.hobby

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.mymidterm.domain.model.TrainingDay

@Composable
fun TrainingDayEntry(
    entry: TrainingDay,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .size(
                width = 140.dp,
                height = 140.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            // Header
            Text(
                text = entry.title,
                style = MaterialTheme.typography.titleMedium
            )
            // Body with preview
            Column() {
                Text(
                    text = entry.description.take(50),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}