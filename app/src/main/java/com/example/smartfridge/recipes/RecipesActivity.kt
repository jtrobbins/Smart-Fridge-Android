package com.example.smartfridge.recipes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R
import com.example.smartfridge.main.NotImplementedActivity
import com.example.smartfridge.shoppinglists.AddShoppingList
import com.example.smartfridge.shoppinglists.ShoppingListsActivity


class RecipesActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var searchView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes)

        searchView = findViewById(R.id.autocomplete_recipe)

        val recipesList = arrayOf("Coconut Cake", "Homemade Lasagna", "Stuffed Bell Peppers", "French Toast", "Broccoli Salad")
        val autocompleteAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.autocomplete_item, recipesList)
        searchView.setAdapter(autocompleteAdapter)

        searchView.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                if (searchView.text.toString() == "Coconut Cake") {
                    val coconutCakeIntent = Intent(this, NotImplementedActivity::class.java)
                    startActivity(coconutCakeIntent)

                } else if (searchView.text.toString() == "Homemade Lasagna") {
                    val homemadeLasagnaIntent = Intent(this, NotImplementedActivity::class.java)
                    startActivity(homemadeLasagnaIntent)

                } else if (searchView.text.toString() == "Stuffed Bell Peppers") {
                    val stuffedBellPeppersIntent = Intent(this, NotImplementedActivity::class.java)
                    startActivity(stuffedBellPeppersIntent)

                } else if (searchView.text.toString() == "French Toast") {
                    val frenchToastIntent = Intent(this, NotImplementedActivity::class.java)
                    startActivity(frenchToastIntent)

                } else if (searchView.text.toString() == "Broccoli Salad") {
                    val broccoliSaladIntent = Intent(this, NotImplementedActivity::class.java)
                    startActivity(broccoliSaladIntent)
                }
            }
            false
        }

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