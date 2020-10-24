package com.example.smartfridge.shoppinglists

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListContentsActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var listViewLists: ListView
    private lateinit var shoppingItemViewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_lists_contents)

        shoppingItemViewModel = ViewModelProviders.of(this, ShoppingListViewModelFactory.getInstance()).get(ShoppingListViewModel::class.java)

        listViewLists = findViewById(R.id.listViewItems)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "List Items"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab2)
        fab.setOnClickListener {
            val addShoppingItemIntent = Intent(this, AddShoppingItem::class.java)
            startActivityForResult(addShoppingItemIntent, ADD_LIST_REQUEST)
        }

        listViewLists.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, item, _ ->
            deleteDialog(item)
            true
        }
    }

    private fun deleteDialog(item: Int) {

        val dialogBuilder = AlertDialog.Builder(this)
        val listId = intent.getIntExtra("ID", 0)

        dialogBuilder.setMessage("Do you want to delete?")

            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    dialog, _ -> shoppingItemViewModel.deleteItems(listId, item)
                val titleAdapter = ItemList(this, shoppingItemViewModel.getItems(listId))
                listViewLists.adapter = titleAdapter
                dialog.dismiss()
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Delete")
        alert.show()
    }

    override fun onStart() {
        super.onStart()

        val listId = intent.getIntExtra("ID", 0)
        val itemAdapter = ItemList(this, shoppingItemViewModel.getItems(listId))
        listViewLists.adapter = itemAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_LIST_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val listId = intent.getIntExtra("ID", 0)
            data.getStringExtra("name")?.let {
                shoppingItemViewModel.addItems(listId, it, data.getStringExtra("quantity")!!
                )
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
                    DialogFragmentShoppingListContentsActivity.newInstance()
                mDialog.show(supportFragmentManager,TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:ShoppingListContentsActivity"
        private val ADD_LIST_REQUEST = 0
    }
}