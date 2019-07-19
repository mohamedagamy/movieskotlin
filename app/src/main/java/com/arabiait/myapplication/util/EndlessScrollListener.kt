package com.arabia_it.baserapp.util

/**
 * Created by Net6 on 11/12/2017.
 */

import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListener : RecyclerView.OnScrollListener {
    // The minimum number of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 5

    constructor(visibleThreshold: Int) {
        this.visibleThreshold = visibleThreshold
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        recyclerView.layoutManager?.let { layoutManager ->

            val totalItemCount = layoutManager.itemCount
            val visibleItemCount = layoutManager.childCount
            var firstVisibleItem = 0
            when (layoutManager) {
                is LinearLayoutManager -> firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                is GridLayoutManager -> firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                else -> return
            }
            if (firstVisibleItem + visibleItemCount + visibleThreshold >= totalItemCount) {
                onLoadMore()
            }
        }

    }

    abstract fun onLoadMore()


}