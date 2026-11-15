package com.example.moviesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.ui.demo.demoMovies
import com.example.moviesapp.ui.model.MovieUiModel
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviePoster(
    movie: MovieUiModel,
    modifier: Modifier= Modifier
){
    Box(
        modifier=modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors=listOf(
                        movie.posterColor,
                        movie.posterColor.copy(alpha = 0.85f),
                        Color(0xFF1C1A17)
                    )
                )
            )
    ){
        Box(
            modifier= Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .size(80.dp)
                .background(
                    Color.White.copy(alpha = 0.10f),
                    CircleShape
                )
        )

        Column(
            modifier= Modifier
                .align(Alignment.BottomStart)
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = movie.title.uppercase(),
                color=Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp,
                lineHeight = 20.sp,
                maxLines=3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoviePosterPreview(){
    MoviesAppTheme {
        MoviePoster(
            movie = demoMovies.first(),
            modifier=Modifier.size(
                width = 180.dp,
                height = 260.dp
            )
        )
    }
}