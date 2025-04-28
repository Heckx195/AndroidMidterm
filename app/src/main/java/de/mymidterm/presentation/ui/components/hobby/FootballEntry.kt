package de.mymidterm.presentation.ui.components.hobby

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.mymidterm.domain.model.FootballMatch

@Composable
fun FootballEntry(
    match: FootballMatch,
    onClick: () -> Unit,
) {
    var visible by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier
            .padding(8.dp)
            .clickable { visible = !visible }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = match.title,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (visible) {
                Text(text = match.description)
                Text(text = match.location)
            }
            Button(onClick = { visible = !visible }) {
                Text(if (visible) "Show Less" else "Show More")
            }
        }
    }
}