package com.arabiait.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_movies)
    }

    override fun onResume() {
        super.onResume()
        reload()
    }

    private fun reload(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MovieAdapter(moviesList)
        }
    }

    private val moviesList = listOf(
            Movie(R.drawable.ic_launcher_background,"One",1992),
            Movie(R.drawable.ic_launcher_background,"Two",1993),
            Movie(R.drawable.ic_launcher_background,"Three",1994),
            Movie(R.drawable.ic_launcher_background,"Four",1995),
            Movie(R.drawable.ic_launcher_background,"Five",1996),
            Movie(R.drawable.ic_launcher_background,"Six",1997),
            Movie(R.drawable.ic_launcher_background,"Seven",1998),
            Movie(R.drawable.ic_launcher_background,"Eight",1999),
            Movie(R.drawable.ic_launcher_background,"Nine",2000),
            Movie(R.drawable.ic_launcher_background,"Ten",2001) )
}
