package com.example.moviesapp.data

import com.example.moviesapp.data.remote.MovieDto
import com.example.moviesapp.data.remote.MoviePage
import com.example.moviesapp.data.remote.TmdbApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieRepositoryTest {

    // Fake API: returns prepared movies instead of calling the network
    private class FakeTmdbApi(
        private val movies: List<MovieDto>
    ) : TmdbApi {
        override suspend fun popular(page: Int): MoviePage {
            return MoviePage(results = movies)
        }
    }

    @Test
    fun repositoryMapsMovieFields() {
        runBlocking {
            val api = FakeTmdbApi(
                listOf(
                    MovieDto(
                        id = 550,
                        title = "Fight Club",
                        releaseDate = "1999-10-15",
                        posterPath = "/poster.jpg",
                        voteAverage = 8.4,
                        overview = "Test overview"
                    )
                )
            )

            val movie = MovieRepository(api).getMovies().first()

            assertEquals("550", movie.id)
            assertEquals("Fight Club", movie.title)
            assertEquals(1999, movie.year)
            assertEquals(8.4, movie.rating, 0.0)
            assertEquals("Test overview", movie.description)
            assertEquals(
                "https://image.tmdb.org/t/p/w500/poster.jpg",
                movie.posterUrl
            )
        }
    }

    @Test
    fun missingReleaseDateGivesYearZero() {
        runBlocking {
            val api = FakeTmdbApi(
                listOf(MovieDto(id = 1, releaseDate = null))
            )

            val movie = MovieRepository(api).getMovies().first()

            assertEquals(0, movie.year)
        }
    }

    @Test
    fun missingPosterGivesNullUrl() {
        runBlocking {
            val api = FakeTmdbApi(
                listOf(MovieDto(id = 1, posterPath = null))
            )

            val movie = MovieRepository(api).getMovies().first()

            assertNull(movie.posterUrl)
        }
    }

    @Test
    fun emptyResponseGivesEmptyList() {
        runBlocking {
            val api = FakeTmdbApi(emptyList())

            val movies = MovieRepository(api).getMovies()

            assertTrue(movies.isEmpty())
        }
    }
}
