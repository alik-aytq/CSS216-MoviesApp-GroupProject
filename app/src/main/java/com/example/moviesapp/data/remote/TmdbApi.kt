package com.example.moviesapp.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/popular")
    suspend fun popular(
        @Query("page") page: Int = 1
    ): MoviePage
}

data class MoviePage(
    val results: List<MovieDto> = emptyList()
)

data class MovieDto(
    val id: Int,
    val title: String = "",
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    val overview: String = ""
)
