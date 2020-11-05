package com.example.smartfridge.recipes

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.R
import androidx.appcompat.widget.SearchView
import com.example.smartfridge.main.MainActivity
import com.example.smartfridge.shoppinglists.AddShoppingList
import com.example.smartfridge.shoppinglists.ShoppingListsActivity


class RecipesActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var filterView: SearchView
    private lateinit var listViewLists: ListView
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var recipeAdapter: ArrayAdapter<Recipes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes)

        recipesViewModel = ViewModelProviders.of(this, RecipesViewModelFactory.getInstance()).get(RecipesViewModel::class.java)

        listViewLists = findViewById(R.id.listViewRecipes)

        filterView = findViewById(R.id.filter_recipe)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Recipes"
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        recipesViewModel.addItem("Broccoli Salad", "Level: Easy","Servings: 10", "Prep Time: 15 min", "Cook Time: 1 hr 15 min")
        recipesViewModel.addItem("Coconut Cake", "Level: Hard", "Servings: 10 to 12", "Prep Time: 35 min", "Cook Time: 50 min")
        recipesViewModel.addItem("French Toast", "Level: Easy","Servings: 4","Prep Time: 20 min", "Cook Time: 10 min")
        recipesViewModel.addItem("Homemade Lasagna", "Level: Intermediate", "Servings: 8", "Prep Time: 40 min", "Cook Time: 1 hr 30 min")
        recipesViewModel.addItem("Stuffed Bell Peppers", "Level: Easy","Servings: 4 to 6", "Prep Time: 45 min", "Cook Time: 45 min" )

        recipeAdapter = RecipeList(this, recipesViewModel.getLists())
        listViewLists.adapter = recipeAdapter

        listViewLists.onItemClickListener = AdapterView.OnItemClickListener { _, view, _, _ ->
            val selected = view.findViewById<TextView>(R.id.textViewRecipeName).text.toString()

            if (selected == "Coconut Cake") {
                val coconutCakeIntent = Intent(this, CoconutCakeActivity::class.java)
                startActivity(coconutCakeIntent)
            } else if (selected == "Homemade Lasagna") {
                val homemadeLasagnaIntent = Intent(this, HomemadeLasagnaActivity::class.java)
                startActivity(homemadeLasagnaIntent)
            } else if (selected == "Stuffed Bell Peppers") {
                val stuffedBellPeppersIntent = Intent(this, StuffedBellPeppersActivity::class.java)
                startActivity(stuffedBellPeppersIntent)
            } else if (selected == "French Toast") {
                val frenchToastIntent = Intent(this, FrenchToastActivity::class.java)
                startActivity(frenchToastIntent)
            } else if (selected == "Broccoli Salad") {
                val broccoliSaladIntent = Intent(this, BroccoliSaladActivity::class.java)
                startActivity(broccoliSaladIntent)
            }
        }

        filterView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                recipeAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeAdapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.recipes_menu, menu)
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
            R.id.clear_all -> {
                val recipeAdapter = RecipeList(this,  recipesViewModel.clearAll())

                listViewLists.adapter = recipeAdapter
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:RecipesActivity"
    }
}