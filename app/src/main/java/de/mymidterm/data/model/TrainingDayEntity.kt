package de.mymidterm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainingday_table")
data class TrainingDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val machines: List<String>,
)

// Info: define the schema for the TrainingDay table in your SQLite database.