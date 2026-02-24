package com.preet.kotlintemplate.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preet.kotlintemplate.base.BaseViewModel
import com.preet.kotlintemplate.network.NetworkResult
import com.preet.kotlintemplate.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    private val repository = AuthRepository()

    private val _loginState = MutableLiveData<NetworkResult<String>>()

    val loginState: LiveData<NetworkResult<String>> get() = _loginState

    fun login(email: String, password: String) {
        _loginState.value = NetworkResult.Loading
        viewModelScopeCustom.launch {
            // Simulate API call
            delay(2000)

            val result = repository.login(email, password)

            _loginState.value = result
        }
    }
}