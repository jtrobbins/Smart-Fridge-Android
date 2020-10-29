package com.example.smartfridge.inventory

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.main.NotImplementedActivity
import java.text.SimpleDateFormat
import java.util.*

class InventoryActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var listViewLists: ListView
    private lateinit var shoppingListViewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        shoppingListViewModel = ViewModelProviders.of(this, ShoppingListViewModelFactory.getInstance()).get(ShoppingListViewModel::class.java)

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
            val addShoppingListIntent = Intent(this, AddInventoryItem::class.java)
            startActivityForResult(addShoppingListIntent, ADD_LIST_REQUEST)
        }

        /*listViewLists.onItemClickListener = AdapterView.OnItemClickListener { _, _, item, _ ->

            val shoppingListContentsIntent = Intent(applicationContext, ShoppingListContentsActivity::class.java)
            shoppingListContentsIntent.putExtra("ID", item)
            startActivity(shoppingListContentsIntent)
        }*/

        listViewLists.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, item, _ ->
            deleteDialog(item)
            true
        }

        generateSample()
    }

    private fun deleteDialog(item: Int) {

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Do you want to delete?")

            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                dialog, _ -> shoppingListViewModel.deleteLists(item)
                val titleAdapter = TitleList(this, shoppingListViewModel.getLists())
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

    private fun generateSample() {
        if (shoppingListViewModel.getLists().size == 0) {
            val cal = Calendar.getInstance()
            val date = SimpleDateFormat("MMM d, yyyy").format(cal.time)

            shoppingListViewModel.addLists("Mary's Birthday!", date)
            shoppingListViewModel.addItems(0, "Cake", "1")
            shoppingListViewModel.addItems(0, "Coke", "2")
            shoppingListViewModel.addItems(0, "Pizza", "3")

            shoppingListViewModel.addLists("Grocery List", date)
            shoppingListViewModel.addItems(1, "Eggs", "12")
            shoppingListViewModel.addItems(1, "Bread", "1")
            shoppingListViewModel.addItems(1, "Milk", "1")
            shoppingListViewModel.addItems(1, "Apples", "8")
            shoppingListViewModel.addItems(1, "Turkey", "1")
        }
    }

    override fun onStart() {
        super.onStart()

        val titleAdapter = TitleList(this, shoppingListViewModel.getLists())

        listViewLists.adapter = titleAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_LIST_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            data.getStringExtra("title")?.let {
                shoppingListViewModel.addLists(it, data.getStringExtra("date")!!)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.shopping_lists_menu, menu)
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
            R.id.delete_all_lists -> {
                val titleAdapter = TitleList(this,  shoppingListViewModel.deleteAllLists())

                listViewLists.adapter = titleAdapter
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