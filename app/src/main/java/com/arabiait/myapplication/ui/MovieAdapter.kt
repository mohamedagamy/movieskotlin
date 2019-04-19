package com.arabiait.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.pojo.ProductionCompaniesItem

class MovieAdapter(movies: List<ProductionCompaniesItem>) : RecyclerView.Adapter<MovieHolder>() {
    private var movieList:List<ProductionCompaniesItem> = movies

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
