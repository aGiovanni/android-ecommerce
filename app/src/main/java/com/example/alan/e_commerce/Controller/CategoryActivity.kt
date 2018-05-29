package com.example.alan.e_commerce.Controller

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.example.alan.e_commerce.Adapters.CategoryRecycleAdapter
import com.example.alan.e_commerce.R
import com.example.alan.e_commerce.Services.DataService
import com.example.alan.e_commerce.Utilities.EXTRA_CATEGORY
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_main.*

class CategoryActivity : AppCompatActivity() {

    lateinit var adapter : CategoryRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        // Set title bar:
        title = "Categories"
        setSupportActionBar(findViewById(R.id.toolbar))

        adapter = CategoryRecycleAdapter(this, DataService.categories) { category ->
            val productIntent = Intent(this, ProductsActivity::class.java)
            productIntent.putExtra(EXTRA_CATEGORY, category.title.toLowerCase())
            startActivity(productIntent)
        }
        categoryListView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        categoryListView.layoutManager = layoutManager


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView
        val searchManager : SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! > 0) {
                    val productIntent = Intent(applicationContext, ProductsActivity::class.java)
                    productIntent.putExtra(EXTRA_CATEGORY, query)
                    startActivity(productIntent)
                } else {
                    Toast.makeText(applicationContext, "Ingrese algo en la búsqueda", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return newText?.length!! > 0
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH.equals(intent?.action)) {
            val query = intent?.getStringExtra(SearchManager.QUERY)
            if (query?.length!! > 0) {
                val productIntent = Intent(applicationContext, ProductsActivity::class.java)
                productIntent.putExtra(EXTRA_CATEGORY, query)
                startActivity(productIntent)
            } else {
                Toast.makeText(applicationContext, "Ingrese algo en la búsqueda", Toast.LENGTH_SHORT).show()
            }
        }
        super.onNewIntent(intent)
    }

    fun loginBtnNavHeaderClicked (view: View) {

    }
}
