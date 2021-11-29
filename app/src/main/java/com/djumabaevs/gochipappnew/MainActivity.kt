package com.djumabaevs.gochipappnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.djumabaevs.gochipappnew.databinding.ActivityMainBinding
import com.djumabaevs.gochipappnew.network.MainViewModel
import com.djumabaevs.gochipappnew.network.MainViewModelFactory
import com.djumabaevs.gochipappnew.network.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val username = binding.etEmail.text
        val password = binding.etPassword.text
        val grantType = binding.etGrant.text
        val clientId = binding.etClient.text
        viewModel.getJWTToken(username.toString(), password.toString(), grantType.toString(), clientId.toString())

        binding.btnLogin.setOnClickListener {
            viewModel.myToken.observe(this, Observer { response ->
                if(response.isSuccessful) {
                    Log.d("Response: ", response.body()?.accessToken.toString())
                    binding.textView.text = response.body()?.accessToken.toString()
                } else {
                    Log.d("Response: ", response.errorBody()?.toString()!!)
                }
            })
                }

    }

//    companion object {
//        var username = "nicholas"
//        var password = "nicholas"
//        var grantType = "password"
//        var clientId = "hasura"
//
//        var lat = ""
//        var lon =""
//    }
}