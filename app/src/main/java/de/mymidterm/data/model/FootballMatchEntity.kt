package de.mymidterm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "footballmatch_table")
data class FootballMatchEntity  (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val location: String,
)

// Info: define the schema for the Note table in your SQLite database.