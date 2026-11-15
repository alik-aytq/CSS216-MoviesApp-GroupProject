package com.example.moviesapp.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesapp.ui.demo.demoMovies
import com.example.moviesapp.ui.model.MovieUiModel
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieCard(
    movie: MovieUiModel,
    isFavorite: Boolean,
    onOpen: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier= Modifier
){
    Card(
        onClick = onOpen,
        modifier=modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier= Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            MoviePoster(
                movie=movie,
                modifier= Modifier
                    .width(100.dp)
                    .height(148.dp)
            )

            Spacer(modifier= Modifier.width(12.dp))

            Column(
                modifier= Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = movie.title,
                        modifier= Modifier.weight(1f),
                        style= MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    IconButton(
                        onClick = onToggleFavorite
                    ) {
                        Icon(
                            imageVector = if (isFavorite){
                                Icons.Filled.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            },
                            contentDescription = if (isFavorite) {
                                "Remove ${movie.title} from favorites"
                            } else {
                                "Add ${movie.title} to favorites"
                            },
                            tint = if(isFavorite){
                                MaterialTheme.colorScheme.primary
                            } else  {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }

                Text(
                    text = "${movie.year} · ${movie.genre}",
                    style = MaterialTheme.typography.bodyMedium,
                    color= MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )

                Text(
                    text="★ ${movie.rating} · ${movie.duration}",
                    style = MaterialTheme.typography.labelMedium,
                    color= MaterialTheme.colorScheme.primary
                )

                Text(
                    text=movie.description,
                    style= MaterialTheme.typography.bodyMedium,
                    color= MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow= TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview(){
    MoviesAppTheme {
        MovieCard(
            movie= demoMovies.first(),
            isFavorite = false,
            onOpen = {},
            onToggleFavorite = {}
        )
    }
}
