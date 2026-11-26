package com.example.moviesapp.data.local

import androidx.compose.ui.graphics.Color
import com.example.moviesapp.ui.model.MovieUiModel

fun SavedMovie.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        title = title,
        year = year,
        genre = genre,
        duration = duration,
        rating = rating,
        description = description,
        posterColor = Color.DarkGray,
        posterUrl = posterUrl
    )
}
