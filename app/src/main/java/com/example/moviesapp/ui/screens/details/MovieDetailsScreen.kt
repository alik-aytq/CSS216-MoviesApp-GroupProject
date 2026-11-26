package com.example.moviesapp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.ui.components.MoviePoster
import com.example.moviesapp.ui.model.MovieUiModel

@Composable
fun MovieDetailsScreen(
    movie: MovieUiModel?,
    isFavorite: Boolean,
    savedNote: String?,
    onBack: ()->Unit,
    onFavoriteClick: () -> Unit,
    onSaveNote: (String) -> Unit
){
    var note by rememberSaveable(movie?.id, savedNote) {
        mutableStateOf(savedNote.orEmpty())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Back")
        }

        if (movie==null){
            Text("Movie not found")
            return@Column
        }
        MoviePoster(
            movie=movie,
            modifier=Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Text(
            text=movie.title,
            style=MaterialTheme.typography.headlineMedium
        )

        Text("${movie.year} · ${movie.genre}")
        Text("Duration: ${movie.duration}")

        Text(
            text="★ ${movie.rating}/10",
            color=MaterialTheme.colorScheme.primary,
            style=MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = movie.description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onFavoriteClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if(isFavorite){
                    "Remove from favorites"
                } else {
                    "Add to favorites"
                }
            )
        }

        if (isFavorite) {
            Text(
                text = "My Note",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Write your note") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Button(
                onClick = {
                    onSaveNote(note)
                },
                enabled = note != savedNote.orEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Note")
            }
        }
    }
}