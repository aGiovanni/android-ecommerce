package com.example.alan.e_commerce.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alan.e_commerce.R
import com.example.alan.e_commerce.Utilities.EXTRA_CATEGORY

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryType = intent.getStringExtra(EXTRA_CATEGORY)
    }
}
