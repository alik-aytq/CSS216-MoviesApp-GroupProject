package com.example.moviesapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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

private val CinemaLightColorScheme = lightColorScheme()

@Composable
fun MoviesAppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            CinemaDarkColorScheme
        } else {
            CinemaLightColorScheme
        },
        typography = CinemaTypography,
        content = content
    )
}
