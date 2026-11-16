package com.example.moviesapp.ui.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MoviesViewModelSearchTest {

    @Test
    fun searchFindsFightClub() {
        val viewModel = MoviesViewModel()

        viewModel.updateSearchText("fight")

        val movies = viewModel.uiState.value.filteredMovies

        assertEquals(1, movies.size)
        assertEquals("Fight Club", movies.first().title)
    }

    @Test
    fun searchIsCaseInsensitive() {
        val viewModel = MoviesViewModel()

        viewModel.updateSearchText("FURY")

        val movies = viewModel.uiState.value.filteredMovies

        assertEquals("Fury", movies.first().title)
    }

    @Test
    fun clearingSearchShowsAllMovies() {
        val viewModel = MoviesViewModel()

        viewModel.updateSearchText("fight")
        viewModel.updateSearchText("")

        assertEquals(
            6,
            viewModel.uiState.value.filteredMovies.size
        )
    }

    @Test
    fun unknownTitleReturnsEmptyList() {
        val viewModel = MoviesViewModel()

        viewModel.updateSearchText("unknown movie 123")

        assertTrue(
            viewModel.uiState.value.filteredMovies.isEmpty()
        )
    }
}
