package com.djumabaevs.gochipappnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.djumabaevs.gochipappnew.network.MainViewModel
import com.djumabaevs.gochipappnew.network.MainViewModelFactory
import com.djumabaevs.gochipappnew.network.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getJWTToken(username, password, grantType, clientId)
        viewModel.myToken.observe(this, Observer { response ->
            if(response.isSuccessful) {
                Log.d("Response: ", response.body()?.accessToken.toString())
            } else {
                Log.d("Response: ", response.errorBody()?.toString()!!)
            }
        })
    }

    companion object {
        var username = "nicholas"
        var password = "nicholas"
        var grantType = "password"
        var clientId = "hasura"
    }
}