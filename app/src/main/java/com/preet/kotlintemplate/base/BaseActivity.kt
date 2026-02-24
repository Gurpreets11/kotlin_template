package com.preet.kotlintemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


//abstract class BaseActivity : AppCompatActivity() {
abstract class BaseActivity<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> VB) : AppCompatActivity() {

    protected lateinit var binding: VB

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
}