package com.arabiait.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(movies: List<Movie>) : RecyclerView.Adapter<MovieHolder>() {
    private var movieList:List<Movie> = movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        //TODO("not implemented")
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        //TODO("not implemented")
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        //TODO("not implemented")
        val movie = movieList[position]
        holder.bind(movie)
    }

}
