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


class RecipesActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var searchView: AutoCompleteTextView
    private lateinit var listViewLists: ListView
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes)

        recipesViewModel =ViewModelProviders.of(this, RecipesViewModelFactory.getInstance()).get(RecipesViewModel::class.java)

        listViewLists = findViewById(R.id.listViewRecipes)

        searchView = findViewById(R.id.autocomplete_recipe)

        val recipesList = arrayOf("Coconut Cake", "Homemade Lasagna", "Stuffed Bell Peppers", "French Toast", "Broccoli Salad")
        val autocompleteAdapter: ArrayAdapter<String> = ArrayAdapter(this, R.layout.autocomplete_item, recipesList)
        searchView.setAdapter(autocompleteAdapter)

        searchView.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {

                if (searchView.text.toString() == "Coconut Cake") {
                    recipesViewModel.addItem("Coconut Cake", "Level: Hard", "Servings: 10 to 12", "Prep Time: 35 min", "Cook Time: 50 min")
                    val recipeAdapter = RecipeList(this, recipesViewModel.getLists())
                    listViewLists.adapter = recipeAdapter

                    val coconutCakeIntent = Intent(this, CoconutCakeActivity::class.java)
                    startActivity(coconutCakeIntent)

                } else if (searchView.text.toString() == "Homemade Lasagna") {
                    recipesViewModel.addItem("Homemade Lasagna", "Level: Intermediate", "Servings: 8", "Prep Time: 40 min", "Cook Time: 1 hr 30 min")
                    val recipeAdapter = RecipeList(this, recipesViewModel.getLists())
                    listViewLists.adapter = recipeAdapter

                    val homemadeLasagnaIntent = Intent(this, HomemadeLasagnaActivity::class.java)
                    startActivity(homemadeLasagnaIntent)

                } else if (searchView.text.toString() == "Stuffed Bell Peppers") {
                    recipesViewModel.addItem("Stuffed Bell Peppers", "Level: Easy","Servings: 4 to 6", "Prep Time: 45 min", "Cook Time: 45 min" )
                    val recipeAdapter = RecipeList(this, recipesViewModel.getLists())
                    listViewLists.adapter = recipeAdapter

                    val stuffedBellPeppersIntent = Intent(this, StuffedBellPeppersActivity::class.java)
                    startActivity(stuffedBellPeppersIntent)

                } else if (searchView.text.toString() == "French Toast") {
                    recipesViewModel.addItem("French Toast", "Level: Easy","Servings: 4","Prep Time: 20 min", "Cook Time: 10 min")
                    val recipeAdapter = RecipeList(this, recipesViewModel.getLists())
                    listViewLists.adapter = recipeAdapter

                    val frenchToastIntent = Intent(this, FrenchToastActivity::class.java)
                    startActivity(frenchToastIntent)

                } else if (searchView.text.toString() == "Broccoli Salad") {
                    recipesViewModel.addItem("Broccoli Salad", "Level: Easy","Servings: 10", "Prep Time: 15 min", "Cook Time: 1 hr 15 min")
                    val recipeAdapter = RecipeList(this, recipesViewModel.getLists())
                    listViewLists.adapter = recipeAdapter

                    val broccoliSaladIntent = Intent(this, BroccoliSaladActivity::class.java)
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
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        toolbar.setNavigationOnClickListener {
            finish()
        }

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

        listViewLists.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, item, _ ->
            deleteDialog(item)
            true
        }
    }

    override fun onStart() {
        super.onStart()

        val recipeAdapter = RecipeList(this, recipesViewModel.getLists())

        listViewLists.adapter = recipeAdapter
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

    private fun deleteDialog(item: Int) {

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Do you want to delete?")

            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    dialog, _ -> recipesViewModel.deleteItem(item)
                val recipeAdapter = RecipeList(this, recipesViewModel.getLists())
                listViewLists.adapter = recipeAdapter
                dialog.dismiss()
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Delete")
        alert.show()
    }

    companion object {
        private const val TAG = "SmartFridge:RecipesActivity"
    }
}