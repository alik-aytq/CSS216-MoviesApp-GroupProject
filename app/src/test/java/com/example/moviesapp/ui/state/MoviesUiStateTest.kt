package com.example.moviesapp.ui.state

import androidx.compose.ui.graphics.Color
import com.example.moviesapp.ui.model.MovieUiModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MoviesUiStateTest {

    private fun movie(id: String, title: String) = MovieUiModel(
        id = id,
        title = title,
        year = 2000,
        genre = "Movie",
        duration = "—",
        rating = 7.0,
        description = "",
        posterColor = Color.DarkGray
    )

    private val movies = listOf(
        movie("1", "Fight Club"),
        movie("2", "Fury"),
        movie("3", "Moneyball")
    )

    @Test
    fun searchFindsFightClub() {
        val state = MoviesUiState(
            movies = movies,
            searchText = "fight"
        )

        assertEquals(1, state.filteredMovies.size)
        assertEquals("Fight Club", state.filteredMovies.first().title)
    }

    @Test
    fun searchIsCaseInsensitive() {
        val state = MoviesUiState(
            movies = movies,
            searchText = "FURY"
        )

        assertEquals("Fury", state.filteredMovies.first().title)
    }

    @Test
    fun searchIgnoresSurroundingSpaces() {
        val state = MoviesUiState(
            movies = movies,
            searchText = "  moneyball  "
        )

        assertEquals("Moneyball", state.filteredMovies.first().title)
    }

    @Test
    fun emptySearchShowsAllMovies() {
        val state = MoviesUiState(
            movies = movies,
            searchText = ""
        )

        assertEquals(3, state.filteredMovies.size)
    }

    @Test
    fun unknownTitleReturnsEmptyList() {
        val state = MoviesUiState(
            movies = movies,
            searchText = "unknown movie 123"
        )

        assertTrue(state.filteredMovies.isEmpty())
    }

    @Test
    fun defaultStatusIsLoading() {
        val state = MoviesUiState()

        assertEquals(ApiStatus.Loading, state.status)
    }
}
