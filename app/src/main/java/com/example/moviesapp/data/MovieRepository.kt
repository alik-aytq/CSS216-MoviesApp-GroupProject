package com.example.moviesapp.data

import androidx.compose.ui.graphics.Color
import com.example.moviesapp.data.remote.TmdbApi
import com.example.moviesapp.ui.model.MovieUiModel

class MovieRepository(
    private val api: TmdbApi
) {
    suspend fun getMovies(): List<MovieUiModel> {
        return api.popular().results.map { dto ->
            MovieUiModel(
                id = dto.id.toString(),
                title = dto.title,
                year = dto.releaseDate
                    ?.take(4)
                    ?.toIntOrNull() ?: 0,
                genre = "Movie",
                duration = "—",
                rating = dto.voteAverage,
                description = dto.overview,
                posterColor = Color.DarkGray,
                posterUrl = dto.posterPath?.let {
                    "https://image.tmdb.org/t/p/w500$it"
                }
            )
        }
    }
}
