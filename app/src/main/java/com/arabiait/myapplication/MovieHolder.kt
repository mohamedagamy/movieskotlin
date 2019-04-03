package com.arabiait.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class MovieHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(R.layout.rv_list_item, parent, false)), View.OnClickListener{
    //here we declare var
    private var mMovieTitle: AppCompatTextView
    private var mMovieYear: AppCompatTextView
    private  var mMovieImage: AppCompatImageView
    private var context: Context

    init {
        context = itemView.context
        mMovieTitle = itemView.findViewById(R.id.main_tv_name)
        mMovieYear = itemView.findViewById(R.id.main_tv_year)
        mMovieImage = itemView.findViewById(R.id.main_iv_pic)

        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(v?.context,"",Toast.LENGTH_SHORT).show()
    }

    fun bind(movie: Movie){
        mMovieTitle?.text = movie.name
        mMovieYear?.text = movie.year.toString()
        mMovieImage?.setImageResource(movie.picture)
    }


}