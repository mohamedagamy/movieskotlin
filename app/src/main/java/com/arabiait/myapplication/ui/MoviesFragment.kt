package com.arabiait.myapplication.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.api.IMDBService
import com.arabiait.myapplication.api.RetrofitBuilder
import com.arabiait.myapplication.pojo.GeneralResponse
import com.arabiait.myapplication.pojo.LatestMovies
import com.arabiait.myapplication.pojo.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {

    companion object {
        var currentPageNumber: Int = 1
    }

    var totalPageCount: Int = 0
    lateinit var recyclerView: RecyclerView
    lateinit var movieAdapter: RecyclerView.Adapter<MovieHolder>
    lateinit var latestMovieAdapter: RecyclerView.Adapter<LatestMovieHolder>
    lateinit var apiCall: Call<GeneralResponse>
    lateinit var apiLatestCall: Call<LatestMovies>
    val retrofit = RetrofitBuilder().createRetrofitObject()
    val service = retrofit.create(IMDBService::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_movies, container, false)
        recyclerView = view.findViewById(R.id.movies_recycleview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentPageNumber = 1
        initScrollListener(tag?.toInt())
        loadMoreMovies(tag?.toInt(), currentPageNumber)

    }


    private fun reloadMovies(moviesList: GeneralResponse) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(null, 2)
            adapter = MovieAdapter(moviesList.results as List<ResultsItem>)
            movieAdapter = adapter as MovieAdapter
        }
    }

    private fun reloadLatestMovies(moviesList: LatestMovies) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = LatestMoviesAdapter(moviesList)
            latestMovieAdapter = adapter as LatestMoviesAdapter
        }
    }


    private fun loadMoreMovies(tag: Int?, page: Int) {
        when (tag) {
            R.id.nav_top_rated -> {
                // Handle the camera action
                Toast.makeText(activity, "Top Rated", Toast.LENGTH_SHORT).show()
                apiCall = service.getTopRatedMovies(page)
            }
            R.id.nav_now_playing -> {
                Toast.makeText(activity, "Now Playing", Toast.LENGTH_SHORT).show()
                apiCall = service.getNowPlayingMovies(page)
            }
            R.id.nav_popular -> {
                Toast.makeText(activity, "Popular", Toast.LENGTH_SHORT).show()
                apiCall = service.getPopularMovies(page)
            }
            R.id.nav_latest -> {
                Toast.makeText(activity, "Latest", Toast.LENGTH_SHORT).show()
                getLatestMovies()

            }
            R.id.nav_upcoming -> {
                Toast.makeText(activity, "Upcoming", Toast.LENGTH_SHORT).show()
                apiCall = service.getUpcomingMovies(page)
            }
        }
        if (tag != R.id.nav_latest) {
            apiCall.enqueue(object : Callback<GeneralResponse> {
                override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                    Log.e("", "" + call.toString())
                }

                override fun onResponse(call: Call<GeneralResponse>, response: Response<GeneralResponse>) {
                    //var currentResource =  if (tag == R.id.nav_latest) response as LatestMovies else response as GeneralResponse
                    val resultList: GeneralResponse = response.body()!!
                    totalPageCount = resultList.totalPages ?: 0
                    if (totalPageCount > 0)
                        reloadMovies(resultList)
                }

            })
        }
    }//End Load More movies

    private fun getLatestMovies() {
        apiLatestCall = service.getLatestMovies()
        apiLatestCall.enqueue(object : Callback<LatestMovies> {
            override fun onFailure(call: Call<LatestMovies>, t: Throwable) {
                Log.e("", "" + call.toString())
            }

            override fun onResponse(call: Call<LatestMovies>, response: Response<LatestMovies>) {
                //var currentResource =  if (tag == R.id.nav_latest) response as LatestMovies else response as GeneralResponse
                val resultList: LatestMovies = response.body()!!
                reloadLatestMovies(resultList)
            }

        })
    }


    fun initScrollListener(tag: Int?) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView
                        .layoutManager as LinearLayoutManager?
                if (linearLayoutManager!!.itemCount
                        <= linearLayoutManager.findLastVisibleItemPosition() + 2) {

                    if (currentPageNumber < totalPageCount) currentPageNumber++ else currentPageNumber = 1

                    Log.e("currentPageNumber: #", "" + currentPageNumber + " / " + totalPageCount)
                    if(tag != R.id.nav_latest)
                        loadMoreMovies(tag, currentPageNumber)
                    //movieAdapter.resetData()

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}//End class
