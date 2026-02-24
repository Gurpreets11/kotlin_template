package com.preet.kotlintemplate.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.preet.kotlintemplate.R
import com.preet.kotlintemplate.base.BaseActivity
import com.preet.kotlintemplate.databinding.ActivitySignupBinding


class SignupActivity :
    BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {

    override fun setupUI() {

        binding.btnSignup.setOnClickListener {

            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            println("Signup: $name, $email")
        }

        binding.tvGoToLogin.setOnClickListener {

            finish() // go back to login
        }
    }

    override fun observeViewModel() {
        // We’ll add ViewModel later
    }
}