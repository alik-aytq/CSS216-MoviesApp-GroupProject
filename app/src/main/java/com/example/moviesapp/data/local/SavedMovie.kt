package com.example.moviesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_movies")
data class SavedMovie(
    @PrimaryKey
    val id: String,
    val title: String,
    val year: Int,
    val genre: String,
    val duration: String,
    val rating: Double,
    val description: String,
    val posterUrl: String?,
    val note: String = ""
)
