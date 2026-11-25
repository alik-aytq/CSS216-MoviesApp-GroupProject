package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.ui.components.SavedMovieEditor
import com.example.moviesapp.ui.navigation.MoviesNavGraph
import com.example.moviesapp.ui.state.ApiStatus
import com.example.moviesapp.ui.theme.MoviesAppTheme
import com.example.moviesapp.ui.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val vm: MoviesViewModel = viewModel()

            val state by vm.uiState.collectAsStateWithLifecycle()
            val saved by vm.savedMovies.collectAsStateWithLifecycle()
            val dark by vm.darkTheme.collectAsStateWithLifecycle()

            var showEditor by remember {
                mutableStateOf(false)
            }

            MoviesAppTheme(darkTheme = dark) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("Dark mode")

                        Switch(
                            checked = dark,
                            onCheckedChange = vm::setDarkTheme
                        )

                        Button(
                            onClick = {
                                showEditor = !showEditor
                            }
                        ) {
                            Text(
                                if (showEditor) {
                                    "Catalog"
                                } else {
                                    "Saved notes"
                                }
                            )
                        }
                    }

                    if (showEditor) {
                        SavedMovieEditor(
                            movies = saved,
                            onUpdate = vm::updateNote,
                            onDelete = vm::deleteSaved
                        )
                    } else {
                        when (val status = state.status) {

                            ApiStatus.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(24.dp)
                                )
                            }

                            ApiStatus.Empty -> {
                                Column {
                                    Text("No movies available")
                                    Button(onClick = vm::retry) {
                                        Text("Retry")
                                    }
                                }
                            }

                            is ApiStatus.Error -> {
                                Column {
                                    Text(
                                        "Could not load movies: ${status.message}"
                                    )
                                    Button(onClick = vm::retry) {
                                        Text("Retry")
                                    }
                                }
                            }

                            ApiStatus.Success -> {
                                MoviesNavGraph(
                                    movies = state.movies,
                                    favoriteIds = state.favoriteIds,
                                    onFavoriteClick = vm::toggleFavorite,
                                    filteredMovies = state.filteredMovies,
                                    searchText = state.searchText,
                                    onSearchTextChange = vm::updateSearchText
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
