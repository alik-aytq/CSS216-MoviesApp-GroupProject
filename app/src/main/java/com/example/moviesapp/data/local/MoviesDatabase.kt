package com.example.moviesapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SavedMovie::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun savedMovieDao(): SavedMovieDao

    companion object {
        @Volatile
        private var instance: MoviesDatabase? = null

        fun get(context: Context): MoviesDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies.db"
                ).build().also {
                    instance = it
                }
            }
    }
}
