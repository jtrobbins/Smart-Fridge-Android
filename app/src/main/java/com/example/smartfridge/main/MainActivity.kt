package com.example.smartfridge.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.*
import com.example.smartfridge.recipes.RecipesActivity
import com.example.smartfridge.shoppinglists.ShoppingListsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Smart Fridge"

        val button1 = findViewById<View>(R.id.button1) as ImageButton
        button1.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
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