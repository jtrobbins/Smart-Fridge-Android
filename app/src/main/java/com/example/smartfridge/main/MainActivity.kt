package com.example.smartfridge.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.*
import com.example.smartfridge.inventory.InventoryActivity
import com.example.smartfridge.inventory.InventoryViewModel
import com.example.smartfridge.inventory.InventoryViewModelFactory
import com.example.smartfridge.recipes.RecipesActivity
import com.example.smartfridge.recipes.RecipesViewModel
import com.example.smartfridge.recipes.RecipesViewModelFactory
import com.example.smartfridge.shoppinglists.ShoppingListViewModel
import com.example.smartfridge.shoppinglists.ShoppingListViewModelFactory
import com.example.smartfridge.shoppinglists.ShoppingListsActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var shoppingListViewModel: ShoppingListViewModel
    private lateinit var inventoryViewModel: InventoryViewModel
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shoppingListViewModel = ViewModelProviders.of(this, ShoppingListViewModelFactory.getInstance()).get(
            ShoppingListViewModel::class.java)
        recipesViewModel = ViewModelProviders.of(this, RecipesViewModelFactory.getInstance()).get(
            RecipesViewModel::class.java)
        inventoryViewModel = ViewModelProviders.of(this, InventoryViewModelFactory.getInstance()).get(
            InventoryViewModel::class.java)

        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar?.title = "Smart Fridge"
        mToolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        val button1 = findViewById<View>(R.id.button1) as ImageButton
        button1.setOnClickListener {
            val inventoryIntent = Intent(this, InventoryActivity::class.java)
            startActivity(inventoryIntent)
        }

        val button2 = findViewById<View>(R.id.button2) as ImageButton
        button2.setOnClickListener {
            val shoppingListsIntent = Intent(this, ShoppingListsActivity::class.java)
            startActivity(shoppingListsIntent)
        }

        val button3 = findViewById<View>(R.id.button3) as ImageButton
        button3.setOnClickListener {
            val recipesIntent = Intent(this, RecipesActivity::class.java)
            startActivity(recipesIntent)
        }

        val button4 = findViewById<View>(R.id.button4) as ImageButton
        button4.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)

        }

        val button5 = findViewById<View>(R.id.button5) as ImageButton
        button5.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

        val button6 = findViewById<View>(R.id.button6) as ImageButton
        button6.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

        generateSample()
    }

    private fun generateSample() {

        val cal = Calendar.getInstance()
        val date = SimpleDateFormat("MMM d, yyyy").format(cal.time)

        shoppingListViewModel.addLists("Mary's Birthday!", date, 2)
        shoppingListViewModel.addItems(0, "Cake", "1", false)
        shoppingListViewModel.addItems(0, "Coke", "2", true)
        shoppingListViewModel.addItems(0, "Pizza", "3", false)

        shoppingListViewModel.addLists("Grocery List", date, 1)
        shoppingListViewModel.addItems(1, "Eggs", "12", false)
        shoppingListViewModel.addItems(1, "Bread", "1", true)
        shoppingListViewModel.addItems(1, "Milk", "1", true)
        shoppingListViewModel.addItems(1, "Apples", "8", false)
        shoppingListViewModel.addItems(1, "Turkey", "1", false)

        inventoryViewModel.addItem("Frozen Pizza", "1", "Jan 29, 2021")
        inventoryViewModel.addItem("Apples", "6", "Nov 10, 2020")
        inventoryViewModel.addItem("Eggs", "12", "Dec 2, 2020")
        inventoryViewModel.addItem("Milk", "1", "Nov 23, 2020")


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more_information -> {
                mDialog =
                    DialogFragmentMainActivity.newInstance()
                mDialog.show(supportFragmentManager,
                    TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:MainActivity"
    }
}