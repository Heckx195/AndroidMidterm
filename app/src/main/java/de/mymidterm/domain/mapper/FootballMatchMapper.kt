package de.mymidterm.domain.mapper

import de.mymidterm.data.model.FootballMatchEntity
import de.mymidterm.domain.model.FootballMatch

// Convert FootballMatchEntity to FootballMatch
fun FootballMatchEntity.toDomain(): FootballMatch {
    return FootballMatch(
        id = this.id,
        title = this.title,
        description = this.description,
        location = this.location
    )
}

// Convert FootballMatch to FootballMatchEntity
fun FootballMatch.toEntity(): FootballMatchEntity {
    return FootballMatchEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        location = this.location
    )
}