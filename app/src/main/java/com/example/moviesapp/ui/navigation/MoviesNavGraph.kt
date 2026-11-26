package com.example.moviesapp.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.ui.model.MovieUiModel
import com.example.moviesapp.ui.screens.home.HomeScreen
import com.example.moviesapp.ui.screens.details.MovieDetailsScreen
import com.example.moviesapp.ui.screens.favorites.FavoritesScreen
import com.example.moviesapp.data.local.SavedMovie
import com.example.moviesapp.data.local.toUiModel
import com.example.moviesapp.ui.state.ApiStatus

private const val HOME = "home"
private const val FAVORITES = "favorites"
private const val DETAILS = "movie/{movieId}"

@Composable
fun MoviesNavGraph(
    apiStatus: ApiStatus,
    onRetry: () -> Unit,
    sortByRating: Boolean,
    onSortByRatingChange: (Boolean) -> Unit,
    movies: List<MovieUiModel>,
    savedMovies: List<SavedMovie>,
    favoriteIds: Set<String>,
    onFavoriteClick: (String) -> Unit,
    onNoteSave: (String, String) -> Unit,
    filteredMovies: List<MovieUiModel>,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (currentRoute == HOME || currentRoute == FAVORITES) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == HOME,
                        onClick = {
                            if (currentRoute != HOME) {
                                navController.popBackStack(HOME, false)
                            }
                        },
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                        },
                        label = { Text("Home") }
                    )

                    NavigationBarItem(
                        selected = currentRoute == FAVORITES,
                        onClick = {
                            if (currentRoute != FAVORITES) {
                                navController.navigate(FAVORITES) {
                                    launchSingleTop = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Favorites"
                            )
                        },
                        label = { Text("Favorites") }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(HOME) {
                HomeScreen(
                    movies = filteredMovies,
                    favoriteIds = favoriteIds,
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    onMovieClick = { movieId ->
                        navController.navigate(
                            "movie/${Uri.encode(movieId)}"
                        )
                    },
                    onFavoriteClick = onFavoriteClick,
                    sortByRating = sortByRating,
                    onSortByRatingChange = onSortByRatingChange,
                    apiStatus = apiStatus,
                    onRetry = onRetry
                )
            }

            composable(FAVORITES) {
                FavoritesScreen(
                    movies = savedMovies,
                    onMovieClick = { movieId ->
                        navController.navigate(
                            "movie/${Uri.encode(movieId)}"
                        )
                    },
                    onFavoriteClick = onFavoriteClick
                )
            }

            composable(
                route = DETAILS,
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                val movieId = entry.arguments?.getString("movieId")

                val movie = movies.firstOrNull {
                    it.id == movieId
                } ?: savedMovies.firstOrNull {
                    it.id == movieId
                }?.toUiModel()

                MovieDetailsScreen(
                    movie = movie,
                    isFavorite = movieId != null &&
                            movieId in favoriteIds,
                    savedNote = savedMovies
                        .firstOrNull { it.id == movieId }
                        ?.note,
                    onBack = {
                        navController.popBackStack()
                    },
                    onFavoriteClick = {
                        movieId?.let(onFavoriteClick)
                    },
                    onSaveNote = { note ->
                        movieId?.let { id ->
                            onNoteSave(id, note)
                        }
                    }
                )
            }
        }
    }
}
