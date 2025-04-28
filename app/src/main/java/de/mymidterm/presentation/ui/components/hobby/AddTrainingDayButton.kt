package de.mymidterm.presentation.ui.components.hobby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.mymidterm.presentation.viewmodel.TrainingDayViewModel
import de.mymidterm.domain.model.TrainingDay

@Composable
fun AddTrainingDayButton(trainingDayViewModel: TrainingDayViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var machines by remember { mutableStateOf("") }

    Button(
        onClick = { showDialog = true },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Add Training Day")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Add Training Day") },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                    OutlinedTextField(
                        value = machines,
                        onValueChange = { machines = it },
                        label = { Text("Machines (comma-separated)") },
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val newTrainingDay = TrainingDay(
                            id = (0..1000).random(), // Zufällige ID
                            title = title,
                            description = description,
                            machines = machines.split(",").map { it.trim() }
                        )
                        trainingDayViewModel.addTrainingDay(newTrainingDay)
                        showDialog = false
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}