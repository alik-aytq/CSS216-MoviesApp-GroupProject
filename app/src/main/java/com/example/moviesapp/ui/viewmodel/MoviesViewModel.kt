package com.example.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.MovieRepository
import com.example.moviesapp.ui.state.MoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MoviesViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _uiState = MutableStateFlow(
        MoviesUiState(
            movies = repository.getMovies()
        )
    )

    val uiState: StateFlow<MoviesUiState> =
        _uiState.asStateFlow()

    fun toggleFavorite(movieId: String) {
        _uiState.update { currentState ->

            // Ignore IDs that are not in the catalog
            if (currentState.movies.none { it.id == movieId }) {
                return@update currentState
            }

            val favorites = currentState.favoriteIds

            val updatedFavorites = if (movieId in favorites) {
                favorites - movieId
            } else {
                favorites + movieId
            }

            currentState.copy(
                favoriteIds = updatedFavorites
            )
        }
    }

    fun updateSearchText(text: String) {
        _uiState.update { currentState ->
            currentState.copy(searchText = text)
        }
    }
}
