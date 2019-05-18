package com.arabiait.myapplication.api

import com.arabiait.myapplication.pojo.GeneralResponse
import com.arabiait.myapplication.pojo.LatestMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface IMDBService {
    @GET("movie/{movie_id}")
    fun listMovies(@Path("movie_id") movie_id: String): Call<GeneralResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page: Int): Call<GeneralResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Call<GeneralResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<GeneralResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Call<GeneralResponse>

    @GET("movie/latest")
    fun getLatestMovies(): Call<LatestMovies>
}