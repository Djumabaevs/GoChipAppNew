package com.djumabaevs.gochipappnew.network


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

const val baseUrl = "https://syncrasy-sso-dev.apps.env02.syncrasy.dev/"

interface TokenApi {
    @FormUrlEncoded
    @POST("https://syncrasy-sso-dev.apps.env02.syncrasy.dev/auth/realms/Syncrasy/protocol/openid-connect/token")
      fun getJWTToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String
    ): Call<TokenResponse>
}

//val retrofit = Retrofit.Builder()
//    .baseUrl(baseUrl)
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()