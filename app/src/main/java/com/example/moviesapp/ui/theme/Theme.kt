package com.example.moviesapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CinemaDarkColorScheme = darkColorScheme(
    primary = CinemaGold,
    onPrimary = CinemaOnGold,

    secondary = CinemaGold,
    onSecondary = CinemaOnGold,

    background = CinemaBackground,
    onBackground = CinemaTextPrimary,

    surface = CinemaSurface,
    onSurface = CinemaTextPrimary,

    surfaceVariant = CinemaSurface,
    onSurfaceVariant = CinemaTextSecondary,

    outline = CinemaOutline
)

@Composable
fun MoviesAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CinemaDarkColorScheme,
        typography = CinemaTypography,
        content = content
    )
}