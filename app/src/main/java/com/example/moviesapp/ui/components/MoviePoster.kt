package com.example.moviesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapp.ui.model.MovieUiModel

@Composable
fun MoviePoster(
    movie: MovieUiModel,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(16.dp)

    if (movie.posterUrl != null) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = "Poster: ${movie.title}",
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(shape)
        )
    } else {
        Box(
            modifier = modifier
                .clip(shape)
                .background(movie.posterColor),
            contentAlignment = Alignment.Center
        ) {
            Text(movie.title)
        }
    }
}
