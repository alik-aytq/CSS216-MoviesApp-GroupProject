package com.example.moviesapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.moviesapp.ui.model.MovieUiModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.ui.components.MovieCard

@Composable
fun HomeScreen(
    movies: List<MovieUiModel>,
    favoriteIds: Set<String>,
    onMovieClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit
){
    var searchText by rememberSaveable {mutableStateOf("")}
    
    val filteredMovies=movies.filter {it.title.contains(searchText.trim(), ignoreCase = true)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = "Movies App",
            style= MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier= Modifier.height(16.dp))

        OutlinedTextField(
            value=searchText,
            onValueChange = {searchText=it},
            label = {Text("Search movies")},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier= Modifier.height(16.dp))
        
        Text(
            text="Movies (${filteredMovies.size})",
            style = MaterialTheme.typography.titleLarge
        )
        
        Spacer(modifier= Modifier.height(12.dp))
        
        if (filteredMovies.isEmpty()){
            Text(
                text = "No movies found",
                color= MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else{
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(
                    items=filteredMovies,
                    key={it.id}
                ) {
                    movie->
                    MovieCard(
                        movie=movie,
                        isFavorite = movie.id in favoriteIds,
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