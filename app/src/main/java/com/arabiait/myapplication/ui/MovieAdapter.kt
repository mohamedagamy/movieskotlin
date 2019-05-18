package com.arabiait.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.pojo.ResultsItem

class MovieAdapter(movies: List<ResultsItem>) : RecyclerView.Adapter<MovieHolder>() {
    private var movieList: List<ResultsItem> = movies
    private var LOADING_ITEM = 200
    private var VIEW_ITEM = 100
    private var ITEM_TYPE = 100

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        //TODO("not implemented")
        val inflater = LayoutInflater.from(parent.context)
        val layoutId = when (ITEM_TYPE) {
            VIEW_ITEM -> R.layout.rv_list_item

            else -> R.layout.rv_list_item
        }
        val view: View = inflater.inflate(layoutId, parent, false)
        return MovieHolder(view)
    }


    override fun getItemCount(): Int {
        //TODO("not implemented")
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        //TODO("not implemented")
        val movie = movieList.get(position)
        holder.bind(movie)
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            itemCount -> ITEM_TYPE = LOADING_ITEM
            else -> ITEM_TYPE = VIEW_ITEM
        }

        return ITEM_TYPE
    }

    fun resetData() {
        (movieList as ArrayList).removeAll(movieList)
        notifyDataSetChanged()
    }


}
