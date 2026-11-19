package com.example.moviesapp.ui.state

import com.example.moviesapp.ui.model.MovieUiModel

data class MoviesUiState(
    val movies: List<MovieUiModel> = emptyList(),
    val favoriteIds: Set<String> = emptySet(),
    val searchText: String = ""
) {
    val filteredMovies: List<MovieUiModel>
        get() = movies.filter {
            it.title.contains(
                searchText.trim(),
                ignoreCase = true
            )
        }
}
