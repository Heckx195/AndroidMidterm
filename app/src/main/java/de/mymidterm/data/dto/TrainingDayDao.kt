package de.mymidterm.data.dto

import androidx.room.*
import de.mymidterm.data.model.TrainingDayEntity

@Dao
interface TrainingDayDao {
    @Insert
    suspend fun insert(trainingDay: TrainingDayEntity)

    @Update
    suspend fun update(trainingDay: TrainingDayEntity)

    @Query("SELECT * FROM trainingday_table")
    suspend fun getAllTrainingDays(): List<TrainingDayEntity>

    @Query("SELECT * FROM trainingday_table WHERE id = :id")
    suspend fun getTrainingDayById(id: Int): TrainingDayEntity?

    @Delete
    suspend fun delete(trainingDay: TrainingDayEntity)
}

// Info: contain the methods to interact with the database.