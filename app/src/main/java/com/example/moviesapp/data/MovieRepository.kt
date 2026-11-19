package com.example.moviesapp.data

import com.example.moviesapp.ui.demo.demoMovies
import com.example.moviesapp.ui.model.MovieUiModel

class MovieRepository {

    fun getMovies(): List<MovieUiModel> {
        return demoMovies
    }
}
