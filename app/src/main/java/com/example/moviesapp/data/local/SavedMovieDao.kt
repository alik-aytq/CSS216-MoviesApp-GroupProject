package com.example.moviesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedMovieDao {

    @Query("SELECT * FROM saved_movies ORDER BY title")
    fun observeAll(): Flow<List<SavedMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: SavedMovie)

    @Query("UPDATE saved_movies SET note = :note WHERE id = :id")
    suspend fun updateNote(id: String, note: String)

    @Query("DELETE FROM saved_movies WHERE id = :id")
    suspend fun delete(id: String)
}
