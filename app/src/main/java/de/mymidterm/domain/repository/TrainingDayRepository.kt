package de.mymidterm.domain.repository

import de.mymidterm.domain.mapper.toDomain
import de.mymidterm.domain.mapper.toEntity
import de.mymidterm.data.dto.TrainingDayDao
import de.mymidterm.domain.model.TrainingDay

class TrainingDayRepository(private val trainingDayDao: TrainingDayDao) {
    suspend fun insertTrainingDay(trainingDay: TrainingDay) {
        trainingDayDao.insert(trainingDay.toEntity())
    }

    suspend fun getTrainingDays(): List<TrainingDay> {
        return trainingDayDao.getAllTrainingDays().map { it.toDomain() }
    }

    suspend fun getTrainingDayById(id: Int): TrainingDay? {
        val trainingDayEntity = trainingDayDao.getTrainingDayById(id)
        return trainingDayEntity?.toDomain()
    }

    suspend fun deleteTrainingDay(trainingDay: TrainingDay) {
        trainingDayDao.delete(trainingDay.toEntity())
    }

    suspend fun updateTrainingDay(trainingDay: TrainingDay) {
        trainingDayDao.update(trainingDay.toEntity())
    }
}

// Info:    fetch data from the database.
//          Use mappers to map toDomain TrainingDay or to Entity TrainingDay