package com.example.alan.e_commerce.Controller

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.GridLayoutManager
import com.example.alan.e_commerce.Adapters.ProductsAdapter
import com.example.alan.e_commerce.R
import com.example.alan.e_commerce.Services.DataService
import com.example.alan.e_commerce.Utilities.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {

    lateinit var adapter : ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        // Set the title in the Action Bar:
        val categoryType = intent.getStringExtra(EXTRA_CATEGORY)
        title = "Products - $categoryType"
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var spanCount = 2

        val orientation = resources.configuration.orientation
        val screenSize = resources.configuration.screenWidthDp

        if (screenSize > 720) {
            spanCount = 3
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                spanCount = 4
            }
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3
        }

        adapter = ProductsAdapter(this, DataService.getProducts(categoryType))
        val layoutManager = GridLayoutManager(this, spanCount)

        productsListView.layoutManager = layoutManager
        productsListView.adapter = adapter
    }
}
