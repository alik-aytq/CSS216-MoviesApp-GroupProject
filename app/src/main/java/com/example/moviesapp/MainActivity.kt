package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.ui.navigation.MoviesNavGraph
import com.example.moviesapp.ui.theme.MoviesAppTheme
import com.example.moviesapp.ui.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MoviesAppTheme {

                val moviesViewModel: MoviesViewModel = viewModel()

                val uiState by moviesViewModel.uiState
                    .collectAsStateWithLifecycle()

                MoviesNavGraph(
                    movies = uiState.movies,
                    favoriteIds = uiState.favoriteIds,
                    onFavoriteClick = moviesViewModel::toggleFavorite
                )
            }
        }
    }
}
