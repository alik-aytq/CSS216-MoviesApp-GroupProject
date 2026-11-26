package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.moviesapp.ui.navigation.MoviesNavGraph
import com.example.moviesapp.ui.state.ApiStatus
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

            MoviesAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                    ) {

                        when (val status = uiState.status) {

                            ApiStatus.Loading -> {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator()

                                    Text(
                                        text = "Loading movies...",
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }

                            ApiStatus.Empty -> {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("No movies available")

                                    Button(
                                        onClick = moviesViewModel::retry
                                    ) {
                                        Text("Retry")
                                    }
                                }
                            }

                            is ApiStatus.Error -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(24.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Could not load movies",
                                        style = MaterialTheme.typography.titleLarge
                                    )

                                    Text(
                                        text = status.message,
                                        modifier = Modifier.padding(12.dp)
                                    )

                                    Button(
                                        onClick = moviesViewModel::retry
                                    ) {
                                        Text("Retry")
                                    }
                                }
                            }

                            ApiStatus.Success -> {
                                MoviesNavGraph(
                                    savedMovies = savedMovies,
                                    onNoteSave = moviesViewModel::updateNote,
                                    movies = uiState.movies,
                                    favoriteIds = uiState.favoriteIds,
                                    onFavoriteClick =
                                        moviesViewModel::toggleFavorite,
                                    filteredMovies = uiState.filteredMovies,
                                    searchText = uiState.searchText,
                                    onSearchTextChange =
                                        moviesViewModel::updateSearchText
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
