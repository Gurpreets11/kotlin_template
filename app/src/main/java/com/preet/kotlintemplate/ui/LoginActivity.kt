package com.preet.kotlintemplate.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.preet.kotlintemplate.base.BaseActivity
import com.preet.kotlintemplate.databinding.ActivityLoginBinding
import com.preet.kotlintemplate.model.User
import com.preet.kotlintemplate.network.NetworkResult
import com.preet.kotlintemplate.utils.AppKeys
import com.preet.kotlintemplate.utils.Navigator
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
            //navigateTo<SignupActivity>()
            Navigator.goTo<SignupActivity>(this)
        }
    }

    override fun observeViewModel() {
        viewModel.loginState.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    showLoading()
                }
                is NetworkResult.Success -> {
                    hideLoading()
                    println(result.data)
                    //navigateTo<MainActivity>()

                    //option 1 .. with no data..
//                    Navigator.goToAndClearStack<MainActivity>(this)


                    // option 2 with single data as string..
                    /*Navigator.goToWithString<MainActivity>(
                        context = this,
                        key = AppKeys.USER_ID,
                        value = "12345",
                        finishCurrent = true
                    )*/


                    // option 3 . with bundle data..
                    val user = User("1", "Gurpreet")

                    Navigator.goToWithParcelable<MainActivity>(
                        context = this,
                        key = AppKeys.USER_OBJECT,
                        value = user
                    )

                    // option 4 .. go with multiple data..

                    Navigator.goToWithData<MainActivity>(
                        context = this,
                        finishCurrent = true,
                        clearStack = true,
                        AppKeys.USER_ID to "101",
                        AppKeys.PRODUCT_ID to "555",
                        AppKeys.VENDOR_ID to "999"
                    )
                }
                is NetworkResult.Error -> {
                    hideLoading()
                    println(result.message)
                }
            }
        }
    }
}