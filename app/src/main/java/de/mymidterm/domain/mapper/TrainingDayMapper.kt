package de.mymidterm.domain.mapper

import de.mymidterm.data.model.TrainingDayEntity
import de.mymidterm.domain.model.TrainingDay

// Convert TrainingDayEntity to TrainingDay
fun TrainingDayEntity.toDomain(): TrainingDay {
    return TrainingDay(
        id = this.id,
        title = this.title,
        description = this.description,
        machines = this.machines
    )
}

// Convert TrainingDay to TrainingDayEntity
fun TrainingDay.toEntity(): TrainingDayEntity {
    return TrainingDayEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        machines = this.machines
    )
}