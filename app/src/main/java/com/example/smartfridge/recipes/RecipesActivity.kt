package com.example.smartfridge.recipes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R


class RecipesActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var searchView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes)

        searchView = findViewById(R.id.autocomplete_recipe)

        val recipesList = arrayOf("Coconut Cake", "Homemade Lasagna", "Stuffed Bell Peppers", "French Toast", "Broccoli Salad")
        val autocompleteAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.autocomplete_item, recipesList)
        searchView.setAdapter(autocompleteAdapter)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Recipes"

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more_information -> {
                mDialog =
                    DialogFragmentRecipesActivity.newInstance()
                mDialog.show(supportFragmentManager,
                    TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:RecipesActivity"
    }
}