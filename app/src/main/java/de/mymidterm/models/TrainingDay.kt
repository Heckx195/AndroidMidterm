package de.mymidterm.models

data class TrainingDay (
    val id: Int,
    var title: String,
    var description: String,
    var machines: List<String>
)