package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.moviesapp.ui.demo.demoMovies
import com.example.moviesapp.ui.navigation.MoviesNavGraph
import com.example.moviesapp.ui.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MoviesAppTheme {
                var favoriteIds by rememberSaveable {
                    mutableStateOf(arrayListOf<String>())
                }

                MoviesNavGraph(
                    movies = demoMovies,
                    favoriteIds = favoriteIds.toSet(),
                    onFavoriteClick = { movieId ->
                        favoriteIds = ArrayList(
                            if (movieId in favoriteIds) {
                                favoriteIds - movieId
                            } else {
                                favoriteIds + movieId
                            }
                        )
                    }
                )
            }
        }
    }
}
