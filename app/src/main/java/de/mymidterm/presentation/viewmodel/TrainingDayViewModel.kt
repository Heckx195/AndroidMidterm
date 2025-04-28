package de.mymidterm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.mymidterm.domain.model.TrainingDay
import de.mymidterm.domain.repository.TrainingDayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrainingDayViewModel(private val repository: TrainingDayRepository) : ViewModel() {
    private val _trainingDays = MutableStateFlow<List<TrainingDay>>(emptyList())
    val trainingDays: StateFlow<List<TrainingDay>> get() = _trainingDays

    private val _trainingDay = MutableStateFlow<TrainingDay?>(null)
    val trainingDay: StateFlow<TrainingDay?> get() = _trainingDay

    // Automatically fetch all trainingDays when the ViewModel is initialized
    init {
        fetchTrainingDays()
    }

    fun fetchTrainingDays() {
        viewModelScope.launch {
            val fetchedTrainingDays = repository.getTrainingDays()
            _trainingDays.value = fetchedTrainingDays
        }
    }

    fun addTrainingDay(trainingDay: TrainingDay) {
        viewModelScope.launch {
            repository.insertTrainingDay(trainingDay)
            fetchTrainingDays()
        }
    }

    fun updateTrainingDay(trainingDay: TrainingDay) {
        viewModelScope.launch {
            repository.updateTrainingDay(trainingDay)
        }
    }

    fun deleteTrainingDay(trainingDay: TrainingDay) {
        viewModelScope.launch {
            repository.deleteTrainingDay(trainingDay)
            fetchTrainingDays()
        }
    }

    fun fetchTrainingDayById(id: Int) {
        viewModelScope.launch {
            val fetchedTrainingDay = repository.getTrainingDayById(id)
            _trainingDay.value = fetchedTrainingDay
        }
    }
}