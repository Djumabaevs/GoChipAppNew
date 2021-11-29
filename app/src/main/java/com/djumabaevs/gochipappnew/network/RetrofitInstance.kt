package com.djumabaevs.gochipappnew.network

import com.djumabaevs.gochipappnew.network.Util.Companion.BASE_URL
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(CallAdapter.Factory)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TokenApi by lazy {
        retrofit.create(TokenApi::class.java)
    }
}