package com.example.moviesapp.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.movieDataStore by preferencesDataStore(
    name = "movie_preferences"
)

class ThemePreferences(
    private val context: Context
) {
    private val DARK = booleanPreferencesKey("dark_theme")

    val darkTheme: Flow<Boolean> =
        context.movieDataStore.data.map {
            it[DARK] ?: true
        }

    suspend fun setDarkTheme(value: Boolean) {
        context.movieDataStore.edit {
            it[DARK] = value
        }
    }
}
