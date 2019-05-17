package com.arabiait.myapplication.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arabiait.myapplication.R
import com.arabiait.myapplication.api.IMDBService
import com.arabiait.myapplication.api.RetrofitBuilder
import com.arabiait.myapplication.pojo.GeneralResponse
import com.arabiait.myapplication.pojo.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    var currentPageNumber:Int = 1
    var totalPageCount:Int = 0
    lateinit var movieAdapter:MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_movies, container, false)
        recyclerView = view.findViewById(R.id.movies_recycleview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScrollListener()
        getUpcomingMovies(currentPageNumber)
    }


    private fun reload(moviesList: GeneralResponse) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(null, 2)
            adapter = MovieAdapter(moviesList.results as List<ResultsItem>)
            movieAdapter = adapter as MovieAdapter
        }
    }

    fun getUpcomingMovies(page:Int) {

        val retrofit = RetrofitBuilder().createRetrofitObject()
        val service = retrofit.create(IMDBService::class.java)
        val movie = service.getUpcomingMovies(page).enqueue(object : Callback<GeneralResponse> {
            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Log.e("", "" + call.toString())
            }

            override fun onResponse(call: Call<GeneralResponse>, response: Response<GeneralResponse>) {
                val resultList: GeneralResponse = response.body()!!
                Log.d("", "" + resultList.toString())
                totalPageCount = response.body()?.totalPages!!
                reload(resultList)
            }

        })

        Log.d("", "" + movie)
    }
    fun initScrollListener()
    {
        //https://www.journaldev.com/24041/android-recyclerview-load-more-endless-scrolling
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView
                        .layoutManager as LinearLayoutManager?
                if (linearLayoutManager!!.itemCount
                        <= linearLayoutManager.findLastVisibleItemPosition() + 2) {

                    if (currentPageNumber< totalPageCount)
                        currentPageNumber++
                    else
                        currentPageNumber = 1

                    Log.e("currentPageNumber: #",""+currentPageNumber)
                    getUpcomingMovies(currentPageNumber)
                    movieAdapter.resetData()

                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }



}//End class









/*    private void loadMore()
    {
        rowsArrayList.add(null);
        recyclerViewAdapter.notifyItemInserted(rowsArrayList.size() - 1);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable () {
            @Override
            public void run() {
                rowsArrayList.remove(rowsArrayList.size() - 1);
                int scrollPosition = rowsArrayList . size ();
                recyclerViewAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize +10;

                while (currentSize - 1 < nextLimit) {
                    rowsArrayList.add("Item " + currentSize);
                    currentSize++;
                }

                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);


    }*/
