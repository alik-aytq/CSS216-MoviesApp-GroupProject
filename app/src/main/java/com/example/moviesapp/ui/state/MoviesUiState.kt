package com.example.moviesapp.ui.state

import com.example.moviesapp.ui.model.MovieUiModel

data class MoviesUiState(
    val movies: List<MovieUiModel> = emptyList(),
    val favoriteIds: Set<String> = emptySet()
)
