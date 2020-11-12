package com.example.smartfridge.recipes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.R


class RecipesActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var filterView: SearchView
    private lateinit var listViewLists: ListView
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var recipeAdapter: ArrayAdapter<Recipes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes)

        findViewById<View>(android.R.id.content).isFocusableInTouchMode = true

        recipesViewModel = ViewModelProviders.of(this, RecipesViewModelFactory.getInstance())
            .get(RecipesViewModel::class.java)

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

        recipesViewModel.addItem(
            "Broccoli Salad",
            "Difficulty: Easy",
            "Servings: 10",
            "Prep Time: 15 min",
            "Cook Time: 1 hr 15 min"
        )
        recipesViewModel.addItem(
            "Coconut Cake",
            "Difficulty: Hard",
            "Servings: 10 to 12",
            "Prep Time: 35 min",
            "Cook Time: 50 min"
        )
        recipesViewModel.addItem(
            "French Toast",
            "Difficulty: Easy",
            "Servings: 4",
            "Prep Time: 20 min",
            "Cook Time: 10 min"
        )
        recipesViewModel.addItem(
            "Homemade Lasagna",
            "Difficulty: Intermediate",
            "Servings: 8",
            "Prep Time: 40 min",
            "Cook Time: 1 hr 30 min"
        )
        recipesViewModel.addItem(
            "Stuffed Bell Peppers",
            "Difficulty: Easy",
            "Servings: 4 to 6",
            "Prep Time: 45 min",
            "Cook Time: 45 min"
        )

        recipeAdapter = RecipeAdapter(this, recipesViewModel.getLists())
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        return super.onTouchEvent(event)
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