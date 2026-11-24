package com.example.moviesapp.data.remote

import com.example.moviesapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbClient {

    val api: TmdbApi by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .header(
                        "Authorization",
                        "Bearer ${BuildConfig.TMDB_TOKEN}"
                    )
                    .header("Accept", "application/json")
                    .build()

                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(TmdbApi::class.java)
    }
}
