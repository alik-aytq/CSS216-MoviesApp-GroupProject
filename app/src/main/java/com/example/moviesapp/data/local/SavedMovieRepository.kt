package com.example.moviesapp.data.local

import com.example.moviesapp.ui.model.MovieUiModel
import kotlinx.coroutines.flow.Flow

class SavedMovieRepository(
    private val dao: SavedMovieDao
) {
    val saved: Flow<List<SavedMovie>> = dao.observeAll()

    suspend fun add(movie: MovieUiModel) {
        dao.insert(
            SavedMovie(
                id = movie.id,
                title = movie.title,
                year = movie.year,
                genre = movie.genre,
                duration = movie.duration,
                rating = movie.rating,
                description = movie.description,
                posterUrl = movie.posterUrl
            )
        )
    }

    suspend fun updateNote(id: String, note: String) {
        dao.updateNote(id, note)
    }

    suspend fun delete(id: String) {
        dao.delete(id)
    }
}
