package com.arabiait.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.api.IMDBService
import com.arabiait.myapplication.api.RetrofitBuilder
import com.arabiait.myapplication.pojo.GeneralResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_movies)

    }

    override fun onStart() {
        super.onStart()
        getUpcomingMovies()

    }

    private fun reload(moviesList: GeneralResponse) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = MovieAdapter(moviesList)
        }
    }


    fun getLatestMovies() {

        val retrofit = RetrofitBuilder().createRetrofitObject()
        val service = retrofit.create(IMDBService::class.java)
        val movie = service.listMovies("1").enqueue(object : Callback<GeneralResponse> {
            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Log.e("", "" + call.toString())
            }

            override fun onResponse(call: Call<GeneralResponse>, response: Response<GeneralResponse>) {
                val resultList: GeneralResponse = response.body()!!
                Log.d("", "" + resultList.toString())
                reload(resultList)
            }

        })

        Log.d("", "" + movie)
    }

    fun getUpcomingMovies() {

        val retrofit = RetrofitBuilder().createRetrofitObject()
        val service = retrofit.create(IMDBService::class.java)
        val movie = service.getUpcomingMovies("2").enqueue(object : Callback<GeneralResponse> {
            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Log.e("", "" + call.toString())
            }

            override fun onResponse(call: Call<GeneralResponse>, response: Response<GeneralResponse>) {
                val resultList: GeneralResponse = response.body()!!
                Log.d("", "" + resultList.toString())
                reload(resultList)
            }

        })

        Log.d("", "" + movie)
    }

}
