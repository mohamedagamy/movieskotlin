package com.arabiait.myapplication.api

import com.arabiait.myapplication.pojo.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface IMDBService {
    @GET("movie/550?api_key=4d48f224b74e38860d09f4d725bccb2a")
    fun listMovies(): Call<MoviesResponse>
}