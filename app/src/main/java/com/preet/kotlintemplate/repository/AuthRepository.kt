package com.preet.kotlintemplate.repository

import com.preet.kotlintemplate.network.NetworkResult
import kotlinx.coroutines.delay
class AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): NetworkResult<String> {

        delay(2000) // simulate network

        return if (email == "admin" && password == "1234") {
            NetworkResult.Success("Login Successful")
        } else {
            NetworkResult.Error("Invalid Credentials")
        }
    }
}