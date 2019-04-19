package com.arabiait.myapplication.api

import com.arabiait.myapplication.pojo.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface IMDBService {
    @GET("movie/{movie_id}")
    fun listMovies(@Path("movie_id") movie_id:String): Call<MoviesResponse>
}