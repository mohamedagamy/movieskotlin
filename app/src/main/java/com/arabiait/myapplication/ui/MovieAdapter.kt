package com.arabiait.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.pojo.GeneralResponse

class MovieAdapter(movies: GeneralResponse) : RecyclerView.Adapter<MovieHolder>() {
    private var movieList: GeneralResponse= movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        //TODO("not implemented")
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.rv_list_item, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        //TODO("not implemented")
        return movieList.results!!.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        //TODO("not implemented")
        val movie = movieList.results!!.get(position)
        holder.bind(movie)
    }

}
