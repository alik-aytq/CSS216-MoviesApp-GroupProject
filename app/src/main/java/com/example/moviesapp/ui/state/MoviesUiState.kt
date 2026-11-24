package com.example.moviesapp.ui.state

import com.example.moviesapp.ui.model.MovieUiModel

sealed interface ApiStatus {
    data object Loading : ApiStatus
    data object Success : ApiStatus
    data object Empty : ApiStatus
    data class Error(
        val message: String
    ) : ApiStatus
}

data class MoviesUiState(
    val movies: List<MovieUiModel> = emptyList(),
    val favoriteIds: Set<String> = emptySet(),
    val searchText: String = "",
    val status: ApiStatus = ApiStatus.Loading
) {
    val filteredMovies: List<MovieUiModel>
        get() = movies.filter {
            it.title.contains(
                searchText.trim(),
                ignoreCase = true
            )
        }
}
