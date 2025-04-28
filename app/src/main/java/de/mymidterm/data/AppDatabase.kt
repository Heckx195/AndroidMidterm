package de.mymidterm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.mymidterm.data.dto.FootballMatchDao
import de.mymidterm.data.dto.TrainingDayDao
import de.mymidterm.data.model.FootballMatchEntity
import de.mymidterm.data.model.TrainingDayEntity

@Database(entities = [FootballMatchEntity::class, TrainingDayEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun footballMatchDao(): FootballMatchDao
    abstract fun trainingDayDao(): TrainingDayDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Design Pattern - Singleton
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "midterm_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Info: defines the Room database with singleton as design pattern.