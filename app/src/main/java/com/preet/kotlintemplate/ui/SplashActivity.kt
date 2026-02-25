package com.preet.kotlintemplate.ui

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.preet.kotlintemplate.base.BaseActivity
import com.preet.kotlintemplate.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity :
    BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun setupUI() {
        lifecycleScope.launch {
            delay(2000)
            navigateTo<LoginActivity>(finishCurrent = true)
        }
    }
    override fun observeViewModel() {
        // No ViewModel needed here
    }
}