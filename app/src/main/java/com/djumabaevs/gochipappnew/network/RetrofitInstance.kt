package com.djumabaevs.gochipappnew.network

import com.djumabaevs.gochipappnew.network.Util.Companion.BASE_URL
import com.djumabaevs.gochipappnew.network.adapter.NetworkResponseAdapter
import com.djumabaevs.gochipappnew.network.adapter.NetworkResponseAdapterFactory
import com.google.gson.GsonBuilder
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
          //  .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(NetworkResponseAdapterFactory())
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val api: TokenApi by lazy {
        retrofit.create(TokenApi::class.java)
    }
}