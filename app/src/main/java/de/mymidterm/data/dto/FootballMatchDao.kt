package de.mymidterm.data.dto

import androidx.room.*
import de.mymidterm.data.model.FootballMatchEntity

@Dao
interface FootballMatchDao {
    @Insert
    suspend fun insert(footballMatch: FootballMatchEntity)

    @Update
    suspend fun update(footballMatch: FootballMatchEntity)

    @Query("SELECT * FROM footballmatch_table")
    suspend fun getAllFootballMatches(): List<FootballMatchEntity>

    @Query("SELECT * FROM footballmatch_table WHERE id = :id")
    suspend fun getFootballMatchById(id: Int): FootballMatchEntity?

    @Delete
    suspend fun delete(footballMatch: FootballMatchEntity)
}

// Info: contain the methods to interact with the database.