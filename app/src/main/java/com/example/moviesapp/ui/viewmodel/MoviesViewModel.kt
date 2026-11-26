package com.example.moviesapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.MovieRepository
import com.example.moviesapp.data.local.MoviesDatabase
import com.example.moviesapp.data.local.SavedMovie
import com.example.moviesapp.data.local.SavedMovieRepository
import com.example.moviesapp.data.preferences.ThemePreferences
import com.example.moviesapp.data.remote.TmdbClient
import com.example.moviesapp.ui.state.ApiStatus
import com.example.moviesapp.ui.state.MoviesUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val remote = MovieRepository(TmdbClient.api)

    private val local = SavedMovieRepository(
        MoviesDatabase.get(application).savedMovieDao()
    )

    private val preferences = ThemePreferences(application)

    private val _uiState = MutableStateFlow(MoviesUiState())

    val uiState: StateFlow<MoviesUiState> =
        _uiState.asStateFlow()

    val savedMovies: StateFlow<List<SavedMovie>> =
        local.saved.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val darkTheme: StateFlow<Boolean> =
        preferences.darkTheme.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            true
        )
    val sortByRating: StateFlow<Boolean> =
        preferences.sortByRating.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    init {
        viewModelScope.launch {
            local.saved.collect { saved ->
                _uiState.update {
                    it.copy(
                        favoriteIds = saved.map { movie ->
                            movie.id
                        }.toSet()
                    )
                }
            }
        }

        retry()
    }

    fun retry() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(status = ApiStatus.Loading)
            }

            try {
                check(BuildConfig.TMDB_TOKEN.isNotBlank()) {
                    "Set TMDB_TOKEN in local.properties"
                }

                val movies = remote.getMovies()

                _uiState.update {
                    it.copy(
                        movies = movies,
                        status = if (movies.isEmpty()) {
                            ApiStatus.Empty
                        } else {
                            ApiStatus.Success
                        }
                    )
                }
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (error: Exception) {
                _uiState.update {
                    it.copy(
                        status = ApiStatus.Error(
                            error.localizedMessage ?: "Network error"
                        )
                    )
                }
            }
        }
    }

    fun setSortByRating(value: Boolean) {
        viewModelScope.launch {
            preferences.setSortByRating(value)
        }
    }

    fun toggleFavorite(movieId: String) {
        val selected = _uiState.value.movies
            .firstOrNull { it.id == movieId }

        viewModelScope.launch {
            if (movieId in _uiState.value.favoriteIds) {
                local.delete(movieId)
            } else if (selected != null) {
                local.add(selected)
            }
        }
    }

    fun deleteSaved(id: String) {
        viewModelScope.launch {
            local.delete(id)
        }
    }

    fun updateNote(id: String, note: String) {
        viewModelScope.launch {
            local.updateNote(id, note)
        }
    }

    fun updateSearchText(text: String) {
        _uiState.update {
            it.copy(searchText = text)
        }
    }

    fun setDarkTheme(value: Boolean) {
        viewModelScope.launch {
            preferences.setDarkTheme(value)
        }
    }
}
