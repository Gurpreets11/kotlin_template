package com.preet.kotlintemplate.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.preet.kotlintemplate.R


//abstract class BaseActivity : AppCompatActivity() {
abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB) : AppCompatActivity() {

    protected lateinit var binding: VB
    private var loadingDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)


        setupUI()
        observeViewModel()
    }

    abstract fun setupUI()

    abstract fun observeViewModel()

    // 🔥 Common Loading Methods

    protected fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = Dialog(this).apply {
                setContentView(R.layout.dialog_loading)
                setCancelable(false)
            }
        }
        loadingDialog?.show()
    }

    protected fun hideLoading() {
        loadingDialog?.dismiss()
    }

    // 🔥 Common Navigation Helper

    protected inline fun <reified T> navigateTo(finishCurrent: Boolean = false) {
        val intent = android.content.Intent(this, T::class.java)
        startActivity(intent)
        if (finishCurrent) finish()
    }
}