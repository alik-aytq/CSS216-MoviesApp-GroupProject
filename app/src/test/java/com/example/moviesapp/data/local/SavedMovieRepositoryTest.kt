package com.example.moviesapp.data.local

import androidx.compose.ui.graphics.Color
import com.example.moviesapp.ui.model.MovieUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SavedMovieRepositoryTest {

    // Fake DAO: keeps movies in memory instead of a real database
    private class FakeSavedMovieDao : SavedMovieDao {

        private val movies = MutableStateFlow<List<SavedMovie>>(emptyList())

        override fun observeAll(): Flow<List<SavedMovie>> = movies

        override suspend fun insert(movie: SavedMovie) {
            movies.value = movies.value.filter { it.id != movie.id } + movie
        }

        override suspend fun updateNote(id: String, note: String) {
            movies.value = movies.value.map {
                if (it.id == id) it.copy(note = note) else it
            }
        }

        override suspend fun delete(id: String) {
            movies.value = movies.value.filter { it.id != id }
        }
    }

    private val fightClub = MovieUiModel(
        id = "550",
        title = "Fight Club",
        year = 1999,
        genre = "Movie",
        duration = "—",
        rating = 8.4,
        description = "Test overview",
        posterColor = Color.DarkGray,
        posterUrl = "https://image.tmdb.org/t/p/w500/poster.jpg"
    )

    @Test
    fun addSavesMovieWithEmptyNote() {
        runBlocking {
            val repository = SavedMovieRepository(FakeSavedMovieDao())

            repository.add(fightClub)

            val saved = repository.saved.first()

            assertEquals(1, saved.size)
            assertEquals("550", saved.first().id)
            assertEquals("Fight Club", saved.first().title)
            assertEquals("", saved.first().note)
        }
    }

    @Test
    fun addingSameMovieTwiceKeepsOneCopy() {
        runBlocking {
            val repository = SavedMovieRepository(FakeSavedMovieDao())

            repository.add(fightClub)
            repository.add(fightClub)

            assertEquals(1, repository.saved.first().size)
        }
    }

    @Test
    fun updateNoteChangesNote() {
        runBlocking {
            val repository = SavedMovieRepository(FakeSavedMovieDao())

            repository.add(fightClub)
            repository.updateNote("550", "Watch again")

            assertEquals(
                "Watch again",
                repository.saved.first().first().note
            )
        }
    }

    @Test
    fun deleteRemovesMovie() {
        runBlocking {
            val repository = SavedMovieRepository(FakeSavedMovieDao())

            repository.add(fightClub)
            repository.delete("550")

            assertTrue(repository.saved.first().isEmpty())
        }
    }
}
