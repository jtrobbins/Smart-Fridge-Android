package com.example.smartfridge.shoppinglists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.main.NotImplementedActivity

class ShoppingListsActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var listViewLists: ListView
    private lateinit var shoppingListViewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_lists)

        shoppingListViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel::class.java)

        listViewLists = findViewById(R.id.listViewTitles)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Shopping Lists"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val addShoppingListIntent = Intent(this, AddShoppingList::class.java)
            startActivityForResult(addShoppingListIntent, ADD_LIST_REQUEST)
        }

        listViewLists.onItemClickListener = AdapterView.OnItemClickListener { _, _, item, _ ->

            val list = shoppingListViewModel.getLists()[item]

            val shoppingListContentsIntent = Intent(applicationContext, NotImplementedActivity::class.java)
            startActivity(shoppingListContentsIntent)
        }

        generateSample()
    }

    override fun onStart() {
        super.onStart()

        val titleAdapter = TitleList(this, shoppingListViewModel.getLists())

        listViewLists.adapter = titleAdapter
    }

    private fun generateSample() {
        shoppingListViewModel.addLists("Mary's Birthday!", "Don't forget it's November 15th!!")
        shoppingListViewModel.addLists("Grocery List", "Lorem Ipsum")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_LIST_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            data.getStringExtra("title")?.let {
                shoppingListViewModel.addLists(it, data.getStringExtra("description")!!)
            }
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
                    DialogFragmentShoppingListsActivity.newInstance()
                mDialog.show(supportFragmentManager,
                    TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:ShoppingListsActivity"
        private val ADD_LIST_REQUEST = 0
    }
}