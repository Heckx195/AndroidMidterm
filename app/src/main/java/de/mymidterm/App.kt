package de.mymidterm

import android.app.Application
import androidx.room.Room
import de.mymidterm.data.AppDatabase
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import de.mymidterm.di.dataModule
import de.mymidterm.di.domainModule
import de.mymidterm.di.presentationModule

class App : Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        // Room database initialise
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "midterm_database"
        ).build()

        // Start Koin Dependency Injection
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}