package com.example.alan.e_commerce.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.app.TaskStackBuilder
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.alan.e_commerce.Model.Product
import com.example.alan.e_commerce.R
import com.example.alan.e_commerce.Utilities.EXTRA_CATEGORY
import com.example.alan.e_commerce.Utilities.EXTRA_PRODUCT

class ProductActivity : AppCompatActivity() {

    lateinit var product : Product
    lateinit var categoryType : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        product = intent.getParcelableExtra(EXTRA_PRODUCT)
        categoryType = intent.getStringExtra(EXTRA_CATEGORY)
        title = product.title
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val productImage = this.findViewById<ImageView>(R.id.productImage)
        val productName = this.findViewById<TextView>(R.id.productName)
        val productCategory = this.findViewById<TextView>(R.id.productCategory)
        val productPrice = this.findViewById<TextView>(R.id.productPrice)

        val resourceId = resources.getIdentifier(product.image,
                "drawable", packageName)
        productImage?.setImageResource(resourceId)
        productName?.text = product.title
        productCategory?.text = product.category
        productPrice?.text = product.price
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                val upIntent: Intent? = NavUtils.getParentActivityIntent(this)

                when {
                    upIntent == null -> throw IllegalStateException("No Parent Activity Intent")
                    NavUtils.shouldUpRecreateTask(this, upIntent) -> {
                        // This activity is NOT part of this app's task, so create a new task
                        // when navigating up, with a synthesized back stack.
                        TaskStackBuilder.create(this)
                                // Add all of this activity's parents to the back stack
                                .addNextIntentWithParentStack(upIntent)
                                // Navigate up to the closest parent
                                .startActivities()
                    }
                    else -> {
                        // This activity is part of this app's task, so simply
                        // navigate up to the logical parent activity.
                        upIntent.putExtra(EXTRA_CATEGORY, categoryType)
                        NavUtils.navigateUpTo(this, upIntent)
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
