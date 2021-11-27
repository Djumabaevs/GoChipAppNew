package com.djumabaevs.gochipappnew.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myToken: MutableLiveData<Response<TokenResponse>> = MutableLiveData()

    fun getJWTToken(
        username: String,
        password: String,
        grantType: String,
        clientId: String
    ) {
        viewModelScope.launch {
            val token: Response<TokenResponse> = repository.getJWTToken(username, password, grantType, clientId)
            myToken.value = token
        }
    }


}