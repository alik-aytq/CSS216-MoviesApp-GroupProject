package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
            val moviesViewModel: MoviesViewModel = viewModel()

            val uiState by moviesViewModel.uiState
                .collectAsStateWithLifecycle()

            val savedMovies by moviesViewModel.savedMovies
                .collectAsStateWithLifecycle()

            val sortByRating by moviesViewModel.sortByRating
                .collectAsStateWithLifecycle()

            MoviesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                    ) {
                        MoviesNavGraph(
                            movies = uiState.movies,
                            savedMovies = savedMovies,
                            favoriteIds = uiState.favoriteIds,
                            onFavoriteClick =
                                moviesViewModel::toggleFavorite,
                            onNoteSave =
                                moviesViewModel::updateNote,
                            filteredMovies = uiState.filteredMovies,
                            searchText = uiState.searchText,
                            onSearchTextChange =
                                moviesViewModel::updateSearchText,
                            sortByRating = sortByRating,
                            onSortByRatingChange =
                                moviesViewModel::setSortByRating,
                            apiStatus = uiState.status,
                            onRetry = moviesViewModel::retry
                        )
                    }
                }
            }
        }
    }
}
