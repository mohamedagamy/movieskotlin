package com.arabiait.myapplication.ui

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.pojo.ResultsItem
import com.arabiait.myapplication.util.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class MovieHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
    //here we declare var
    private var mMovieTitle: AppCompatTextView
    private var mMovieYear: AppCompatTextView
    private var mMovieImage: AppCompatImageView
    private var context: Context

    init {
        context = itemView.context
        mMovieTitle = itemView.findViewById(R.id.main_tv_name)
        mMovieYear = itemView.findViewById(R.id.main_tv_year)
        mMovieImage = itemView.findViewById(R.id.main_iv_pic)


        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(v?.context, "" + v?.tag, Toast.LENGTH_SHORT).show()
    }

    fun bind(movie: ResultsItem?) {
        mMovieTitle.text = movie?.title
        mMovieYear.text = movie?.releaseDate.toString()
        Picasso.get().load(IMAGE_BASE_URL +movie?.posterPath).into(mMovieImage)

        itemView.setTag(mMovieTitle.text)
    }


}