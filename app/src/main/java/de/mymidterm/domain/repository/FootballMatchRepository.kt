package de.mymidterm.domain.repository

import de.mymidterm.domain.mapper.toDomain
import de.mymidterm.domain.mapper.toEntity
import de.mymidterm.data.dto.FootballMatchDao
import de.mymidterm.domain.model.FootballMatch

class FootballMatchRepository(private val footballMatchDao: FootballMatchDao) {
    suspend fun insertFootballMatch(footballMatch: FootballMatch) {
        footballMatchDao.insert(footballMatch.toEntity())
    }

    suspend fun getFootballMatches(): List<FootballMatch> {
        return footballMatchDao.getAllFootballMatches().map { it.toDomain() }
    }

    suspend fun getFootballMatchById(id: Int): FootballMatch? {
        val noteEntity = footballMatchDao.getFootballMatchById(id)
        return noteEntity?.toDomain()
    }

    suspend fun deleteFootballMatch(note: FootballMatch) {
        footballMatchDao.delete(note.toEntity())
    }

    suspend fun updateFootballMatch(note: FootballMatch) {
        footballMatchDao.update(note.toEntity())
    }
}

// Info:    fetch data from the database.
//          Use mappers to map toDomain FootballMatch or to Entity FootballMatch