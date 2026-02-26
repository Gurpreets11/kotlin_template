package com.preet.kotlintemplate.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.preet.kotlintemplate.base.BaseActivity
import com.preet.kotlintemplate.databinding.ActivitySplashBinding
import com.preet.kotlintemplate.utils.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity :
    BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun setupUI() {
        /*lifecycleScope.launch {
            delay(2000)
            //navigateTo<LoginActivity>(finishCurrent = true)
            Navigator.goTo<LoginActivity>(
                context = this@SplashActivity,
                finishCurrent = true
            )
        }*/

        val background = binding.root.background as AnimationDrawable
        background.start()

        startAnimation();
    }
    override fun observeViewModel() {
        // No ViewModel needed here
    }


    private fun startAnimation() {

        // Logo animation
        binding.ivLogo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(900)
            .start()

        // App name fade
        binding.tvAppName.animate()
            .alpha(1f)
            .setStartDelay(400)
            .setDuration(800)
            .start()

        // Tagline fade
        binding.tvTagline.animate()
            .alpha(1f)
            .setStartDelay(700)
            .setDuration(800)
            .start()

        // Navigate after delay
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 4200)

        startFloatingAnimation()
    }


    private fun startFloatingAnimation() {
        val animator = ObjectAnimator.ofFloat(
            binding.ivLogo,
            "translationY",
            -20f,
            20f
        ).apply {
            duration = 2000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        animator.start()
    }

}