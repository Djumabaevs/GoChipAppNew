package com.djumabaevs.gochipappnew.network

import retrofit2.Response

class Repository {

    suspend fun getJWTToken(
        username: String,
        password: String,
        grantType: String,
        clientId: String
    ): Response<TokenResponse> {
        return RetrofitInstance.api.getJWTToken(username, password, grantType, clientId)
    }
}