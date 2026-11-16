package com.example.moviesapp.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieRepositoryTest {

    private val repository = MovieRepository()

    @Test
    fun repositoryReturnsMovies() {
        val movies = repository.getMovies()

        assertEquals(6, movies.size)
    }

    @Test
    fun repositoryContainsFightClub() {
        val movies = repository.getMovies()

        assertTrue(
            movies.any { it.title == "Fight Club" }
        )
    }

    @Test
    fun movieIdsAreUnique() {
        val movies = repository.getMovies()

        val ids = movies.map { it.id }

        assertEquals(ids.size, ids.toSet().size)
    }
}
