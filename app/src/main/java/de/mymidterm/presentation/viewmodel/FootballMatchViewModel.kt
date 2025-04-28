package de.mymidterm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.mymidterm.domain.model.FootballMatch
import de.mymidterm.domain.repository.FootballMatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FootballMatchViewModel(private val repository: FootballMatchRepository) : ViewModel() {
    private val _footballMatches = MutableStateFlow<List<FootballMatch>>(emptyList())
    val footballMatches: StateFlow<List<FootballMatch>> get() = _footballMatches

    private val _footballMatch = MutableStateFlow<FootballMatch?>(null)
    val footballMatch: StateFlow<FootballMatch?> get() = _footballMatch

    // Automatically fetch all footballMatches when the ViewModel is initialized
    init {
        fetchFootballMatches()
    }

    fun fetchFootballMatches() {
        viewModelScope.launch {
            val fetchedFootballMatches = repository.getFootballMatches()
            _footballMatches.value = fetchedFootballMatches
        }
    }

    fun addFootballMatch (footballMatch: FootballMatch) {
        viewModelScope.launch {
            repository.insertFootballMatch(footballMatch)
            fetchFootballMatches()
        }
    }

    fun updateFootballMatch(footballMatch: FootballMatch) {
        viewModelScope.launch {
            repository.updateFootballMatch(footballMatch)
        }
    }

    fun deleteFootballMatch(footballMatch: FootballMatch) {
        viewModelScope.launch {
            repository.deleteFootballMatch(footballMatch)
            fetchFootballMatches()
        }
    }

    fun fetchNoteById(id: Int) {
        viewModelScope.launch {
            val fetchedFootballMatch = repository.getFootballMatchById(id)
            _footballMatch.value = fetchedFootballMatch
        }
    }
}