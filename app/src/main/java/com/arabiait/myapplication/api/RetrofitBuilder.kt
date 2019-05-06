package com.arabiait.myapplication.api

import com.arabiait.myapplication.util.API_KEY
import com.arabiait.myapplication.util.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {


    class MyInterceptor : okhttp3.Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val httpUrl = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
            request = request.newBuilder().url(httpUrl).build()
            return chain.proceed(request)
        }

    }


    fun createRetrofitObject(): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().addInterceptor(MyInterceptor()).build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }


}