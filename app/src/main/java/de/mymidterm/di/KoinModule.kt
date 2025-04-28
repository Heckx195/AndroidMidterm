package de.mymidterm.di

import android.app.Application
import de.mymidterm.data.AppDatabase
import de.mymidterm.data.dto.FootballMatchDao
import de.mymidterm.data.dto.TrainingDayDao
import de.mymidterm.domain.repository.FootballMatchRepository
import de.mymidterm.domain.repository.TrainingDayRepository
import de.mymidterm.presentation.viewmodel.FootballMatchViewModel
import de.mymidterm.presentation.viewmodel.TrainingDayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { AppDatabase.getDatabase(get<Application>()) }
    single { get<AppDatabase>().footballMatchDao() }
    single { get<AppDatabase>().trainingDayDao() }
}

val domainModule = module {
    single { FootballMatchRepository(get<FootballMatchDao>()) }
    single { TrainingDayRepository(get<TrainingDayDao>()) }
}

val presentationModule = module {
    viewModel { FootballMatchViewModel(get()) }
    viewModel { TrainingDayViewModel(get()) }
}

// Info: provide the database and DAO dependencies.