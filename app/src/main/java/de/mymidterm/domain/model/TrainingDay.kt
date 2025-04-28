package de.mymidterm.domain.model

data class TrainingDay (
    val id: Int,
    var title: String,
    var description: String,
    var machines: List<String>
)