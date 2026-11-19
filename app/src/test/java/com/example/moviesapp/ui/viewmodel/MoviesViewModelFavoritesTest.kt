package com.example.moviesapp.ui.viewmodel

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MoviesViewModelFavoritesTest {

    @Test
    fun addMovieToFavorites() {
        val viewModel = MoviesViewModel()

        viewModel.toggleFavorite("fight-club")

        assertTrue(
            "fight-club" in viewModel.uiState.value.favoriteIds
        )
    }

    @Test
    fun removeMovieFromFavorites() {
        val viewModel = MoviesViewModel()

        viewModel.toggleFavorite("fight-club")
        viewModel.toggleFavorite("fight-club")

        assertFalse(
            "fight-club" in viewModel.uiState.value.favoriteIds
        )
    }

    @Test
    fun unknownMovieIsNotAdded() {
        val viewModel = MoviesViewModel()

        viewModel.toggleFavorite("unknown-movie")

        assertTrue(
            viewModel.uiState.value.favoriteIds.isEmpty()
        )
    }
}
