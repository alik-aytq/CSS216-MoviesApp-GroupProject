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

    private val SORT_BY_RATING =
        booleanPreferencesKey("sort_by_rating")
    val darkTheme: Flow<Boolean> =
        context.movieDataStore.data.map {
            it[DARK] ?: true
        }

    val sortByRating: Flow<Boolean> =
        context.movieDataStore.data.map {
            it[SORT_BY_RATING] ?: false
        }
    suspend fun setDarkTheme(value: Boolean) {
        context.movieDataStore.edit {
            it[DARK] = value
        }
    }
    suspend fun setSortByRating(value: Boolean) {
        context.movieDataStore.edit {
            it[SORT_BY_RATING] = value
        }
    }
}
