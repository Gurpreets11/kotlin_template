package com.preet.kotlintemplate.base


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel : ViewModel() {

    private val job = SupervisorJob()

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleException(throwable)
    }

    protected val viewModelScopeCustom =
        CoroutineScope(Dispatchers.Main + job + coroutineExceptionHandler)

    open fun handleException(throwable: Throwable) {
        // Centralized error handling
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}