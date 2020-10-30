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
    private lateinit var inventoryListViewModel: InventoryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        inventoryListViewModel = ViewModelProviders.of(this, InventoryListViewModelFactory.getInstance()).get(InventoryListViewModel::class.java)

        listViewLists = findViewById(R.id.listViewTitles)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Fridge Inventory"

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val addInventoryItemIntent = Intent(this, AddInventoryItem::class.java)
            startActivityForResult(addInventoryItemIntent, ADD_ITEM_REQUEST)
        }

        val fridgeCam = findViewById<ImageView>(R.id.cameraIcon)
        fridgeCam.setOnClickListener {
            val fridgeCamIntent = Intent(this, FridgeCamView::class.java)
            startActivity(fridgeCamIntent)
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
                dialog, _ -> inventoryListViewModel.deleteItem(item)
                val titleAdapter = TitleList(this, inventoryListViewModel.getItems())
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
        if (inventoryListViewModel.getItems().size == 0) {
            val date = "1/5/2020"
            inventoryListViewModel.addItem("Frozen Pizza", "1 box", date)
        }
    }

    override fun onStart() {
        super.onStart()

        val titleAdapter = TitleList(this, inventoryListViewModel.getItems())

        listViewLists.adapter = titleAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            data.getStringExtra("name")?.let {
                inventoryListViewModel.addItem(it, data.getStringExtra("quantity")!!, data.getStringExtra("expiration")!!)
            }
        }

    }

    companion object {
        private const val TAG = "SmartFridge:InventoryActivity"
        private val ADD_ITEM_REQUEST = 0
    }
}