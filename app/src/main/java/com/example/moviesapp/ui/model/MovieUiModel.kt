package com.example.moviesapp.ui.model

import androidx.compose.ui.graphics.Color

data class MovieUiModel(
    val id: String,
    val title: String,
    val year: Int,
    val genre: String,
    val duration: String,
    val rating: Double,
    val description: String,
    val posterColor: Color,
    val posterUrl: String? = null
)