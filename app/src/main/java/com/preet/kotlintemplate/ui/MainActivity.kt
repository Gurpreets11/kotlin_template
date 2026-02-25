package com.preet.kotlintemplate.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.preet.kotlintemplate.R
import com.preet.kotlintemplate.model.User
import com.preet.kotlintemplate.utils.AppKeys

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // option 2.. receive data in string..
        /*val userId = intent.getStringExtra(AppKeys.USER_ID)
        println("UserId: $userId")*/

        // option 3 : receive data in bundle..
        val user = intent.getParcelableExtra<User>(AppKeys.USER_OBJECT)
        println(user?.name)

        // option 4..
        val userId = intent.getStringExtra(AppKeys.USER_ID)
        val productId = intent.getStringExtra(AppKeys.PRODUCT_ID)
        val vendorId = intent.getStringExtra(AppKeys.VENDOR_ID)
    }
}