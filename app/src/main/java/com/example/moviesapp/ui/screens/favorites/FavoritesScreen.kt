package com.example.moviesapp.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.ui.model.MovieUiModel
import androidx.compose.foundation.lazy.items
import com.example.moviesapp.ui.components.MovieCard

@Composable
fun FavoritesScreen(
    movies: List<MovieUiModel>,
    onMovieClick: (String)->Unit,
    onFavoriteClick: (String)-> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorites",
            style= MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier=Modifier.height(16.dp))

        if(movies.isEmpty()){
            Text(
                text="You have no favorite movies yet.",
                color= MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom=16.dp)
            ) {
                items(
                    items=movies,
                    key={it.id}
                ) {movie ->
                    MovieCard(
                        movie=movie,
                        isFavorite = true,
                        onOpen = {
                            onMovieClick(movie.id)
                        },
                        onToggleFavorite = {
                            onFavoriteClick(movie.id)
                        }
                    )
                }

            }
        }
    }
}