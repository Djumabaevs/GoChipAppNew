package com.djumabaevs.gochipappnew.network


import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


interface TokenApi {
    @FormUrlEncoded
    @POST("openid-connect/token")
      suspend fun getJWTToken(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String
    ): Response<TokenResponse>
}
