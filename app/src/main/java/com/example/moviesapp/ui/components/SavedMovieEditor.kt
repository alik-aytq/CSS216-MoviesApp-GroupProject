package com.example.moviesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.data.local.SavedMovie

@Composable
fun SavedMovieEditor(
    movies: List<SavedMovie>,
    onUpdate: (String, String) -> Unit,
    onDelete: (String) -> Unit
) {
    if (movies.isEmpty()) {
        Text(
            text = "No saved movies yet",
            modifier = Modifier.padding(20.dp)
        )
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies, key = { it.id }) { movie ->

            var note by remember(movie.id, movie.note) {
                mutableStateOf(movie.note)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(movie.title)

                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Your note") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            onUpdate(movie.id, note)
                        }
                    ) {
                        Text("Save note")
                    }

                    Button(
                        onClick = {
                            onDelete(movie.id)
                        }
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
