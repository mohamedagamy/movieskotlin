package com.arabiait.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.pojo.LatestMovies
import com.arabiait.myapplication.pojo.ResultsItem

class LatestMoviesAdapter(movies:LatestMovies): RecyclerView.Adapter<LatestMovieHolder>() {
    private var latestmovie: LatestMovies= movies
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestMovieHolder {
        var view: View =LayoutInflater.from(parent.context).inflate(R.layout.rv_list_item,parent,false)
        return LatestMovieHolder(view)
    }

    override fun getItemCount(): Int {

        return 1
    }

    override fun onBindViewHolder(holder: LatestMovieHolder, position: Int) {
        val movie = latestmovie
        holder.bind(movie)
    }

}