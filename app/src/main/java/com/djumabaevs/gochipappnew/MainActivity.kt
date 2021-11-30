package com.djumabaevs.gochipappnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.djumabaevs.gochipappnew.databinding.ActivityMainBinding
import com.djumabaevs.gochipappnew.details.DetailsActivity
import com.djumabaevs.gochipappnew.network.MainViewModel
import com.djumabaevs.gochipappnew.network.MainViewModelFactory
import com.djumabaevs.gochipappnew.network.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        binding.btnLogin.setOnClickListener {
            viewModel.getJWTToken(username.toString(), password.toString(), grantType.toString(), clientId.toString())
            viewModel.myToken.observe(this, Observer { response ->
                Log.d("Response: ", response.body()?.accessToken.toString())
                binding.textView.text = response.body()?.accessToken.toString()
                if(response.isSuccessful) {
                    val intent = Intent(this, DetailsActivity::class.java)
                    startActivity(intent)
                    finish()
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