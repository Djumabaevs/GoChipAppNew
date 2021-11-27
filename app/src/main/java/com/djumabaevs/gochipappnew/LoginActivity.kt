package com.djumabaevs.gochipappnew

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.djumabaevs.gochipappnew.databinding.ActivityLoginBinding
import com.djumabaevs.gochipappnew.network.TokenApi
import com.djumabaevs.gochipappnew.network.TokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(), View.OnClickListener {

    private var mContext: Context? = null
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.btnLogin.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_login -> {
                    getCurrentData()
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            else -> {
                true
            }
        }
    }

    private fun logInRegisteredUser() {
//        if (validateLoginDetails()) {
           // showProgressDialog(resources.getString(R.string.please_wait))

//        }
    }

    val BaseUrl = "https://syncrasy-sso-dev.apps.env02.syncrasy.dev/"


     fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TokenApi::class.java)
        val call = service.getJWTToken(username, password, grantType, clientId)
        call.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {

                    val tokenResponse = response.body()

                    binding.tvTitle.text = tokenResponse?.tokenType

            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {

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