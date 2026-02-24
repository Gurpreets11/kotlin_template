package com.preet.kotlintemplate.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.preet.kotlintemplate.base.BaseActivity
import com.preet.kotlintemplate.databinding.ActivityLoginBinding
import com.preet.kotlintemplate.network.NetworkResult
import com.preet.kotlintemplate.viewmodel.LoginViewModel


class LoginActivity :
    BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun setupUI() {
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(email, password)
        }

        binding.tvGoToSignup.setOnClickListener {

            startActivity(
                Intent(this, SignupActivity::class.java)
            )
        }
    }

    override fun observeViewModel() {

        viewModel.loginState.observe(this) { result ->

            when (result) {

                is NetworkResult.Loading -> {
                    println("Loading...")
                }

                is NetworkResult.Success -> {
                    println(result.data)
                }

                is NetworkResult.Error -> {
                    println(result.message)
                }
            }
        }
    }
}