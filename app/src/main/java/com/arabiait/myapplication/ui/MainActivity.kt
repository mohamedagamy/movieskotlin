package com.arabiait.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.api.IMDBService
import com.arabiait.myapplication.api.RetrofitBuilder
import com.arabiait.myapplication.pojo.MoviesResponse
import com.arabiait.myapplication.pojo.ProductionCompaniesItem
import com.arabiait.myapplication.util.API_KEY
import com.arabiait.myapplication.util.BASE_URL
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_movies)

    }

    override fun onStart() {
        super.onStart()
        createApiCall()

    }

    private fun reload(moviesList: List<ProductionCompaniesItem>) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = MovieAdapter(moviesList)
        }
    }


    fun createApiCall() {

        val retrofit = RetrofitBuilder().createRetrofitObject()
        val service = retrofit.create(IMDBService::class.java)
        val movie = service.listMovies("550").enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("", "" + call.toString())
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val resultList: MoviesResponse = response.body()!!
                Log.d("", "" + resultList.toString())
                val moviesList = resultList?.productionCompanies as List<ProductionCompaniesItem>
                reload(moviesList)
            }

        })

        Log.d("", "" + movie)
    }

}
