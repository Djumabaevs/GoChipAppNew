package com.djumabaevs.gochipappnew.network

import com.djumabaevs.gochipappnew.network.adapter.NetworkResponse
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

    suspend fun fetchSurcharge(lat: String, lon: String) : Response<SurchargeResponse> {
        return RetrofitInstance.api.fetchSurcharge(lat, lon)
    }

    suspend fun getData(): Response<List<DataResponse>> {
        return RetrofitInstance.api.getData()
    }
}