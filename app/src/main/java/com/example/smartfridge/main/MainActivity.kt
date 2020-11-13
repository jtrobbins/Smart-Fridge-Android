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
        val date = SimpleDateFormat("MM/dd/yyyy").format(cal.time)

        shoppingListViewModel.addLists("Joey's Birthday!", date, 2)
        shoppingListViewModel.addItems(0, "Candles", "14", false)
        shoppingListViewModel.addItems(0, "Chocolate Icing", "2", false)
        shoppingListViewModel.addItems(0, "Mozzarella Cheese", "2", false)
        shoppingListViewModel.addItems(0, "Tomato", "4", true)
        shoppingListViewModel.addItems(0, "Onion", "1", true)
        shoppingListViewModel.addItems(0, "California Style Bread", "1", false)
        shoppingListViewModel.addItems(0, "Cake Batter", "1", false)
        shoppingListViewModel.addItems(0, "Party Cups", "20", true)
        shoppingListViewModel.addItems(0, "Paper Plates", "20", true)
        shoppingListViewModel.addItems(0, "Napkins", "20", false)
        shoppingListViewModel.addItems(0, "Plastic Silverware", "20", false)
        shoppingListViewModel.addItems(0, "Butter", "1", true)

        shoppingListViewModel.addLists("Martha's Wedding (Buffet Style)", date, 4)
        shoppingListViewModel.addItems(1, "Silverware", "150", true)
        shoppingListViewModel.addItems(1, "Lobster", "70", false)
        shoppingListViewModel.addItems(1, "Mashed Potatoes", "132", true)
        shoppingListViewModel.addItems(1, "Mac and Cheese", "122", true)
        shoppingListViewModel.addItems(1, "Salad", "150", true)
        shoppingListViewModel.addItems(1, "Grilled Chicken", "117", true)
        shoppingListViewModel.addItems(1, "Hamburger Meat", "60", true)
        shoppingListViewModel.addItems(1, "Napkins", "150", true)
        shoppingListViewModel.addItems(1, "Barbeque Sauce", "20", false)
        shoppingListViewModel.addItems(1, "Mayo", "20", true)
        shoppingListViewModel.addItems(1, "Mustard", "20", true)
        shoppingListViewModel.addItems(1, "Ketchup", "20", true)
        shoppingListViewModel.addItems(1, "Ribs", "60", false)

        shoppingListViewModel.addLists("Fannie Mae Catering Event", date, 3)
        shoppingListViewModel.addItems(2, "Calamari", "15", false)
        shoppingListViewModel.addItems(2, "Chicken Tenders", "60", true)
        shoppingListViewModel.addItems(2, "Cod", "40", true)
        shoppingListViewModel.addItems(2, "Blue Crab Legs", "35", false)
        shoppingListViewModel.addItems(2, "Crab Cakes", "35", true)
        shoppingListViewModel.addItems(2, "Tartar Sauce", "15", true)
        shoppingListViewModel.addItems(2, "Oysters", "15", false)
        shoppingListViewModel.addItems(2, "Brown Rice", "60", true)
        shoppingListViewModel.addItems(2, "White Rice", "60", true)
        shoppingListViewModel.addItems(2, "Sangria", "40", true)

        shoppingListViewModel.addLists("Grocery List", date, 1)
        shoppingListViewModel.addItems(3, "Eggs", "12", false)
        shoppingListViewModel.addItems(3, "Bread", "1", true)
        shoppingListViewModel.addItems(3, "Milk", "1", true)
        shoppingListViewModel.addItems(3, "Health-Ade Kombucha", "1", false)
        shoppingListViewModel.addItems(3, "Apples", "8", false)
        shoppingListViewModel.addItems(3, "Turkey", "1", false)

        inventoryViewModel.addItem("Frozen Pizza", "1", "1/29/2021")
        inventoryViewModel.addItem("Apples", "6", "11/10/2020")
        inventoryViewModel.addItem("Eggs", "12", "12/2/2020")
        inventoryViewModel.addItem("Health-Ade Kombucha", "1", "2/10/2021")
        inventoryViewModel.addItem("Milk", "1", "11/23/2020")


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