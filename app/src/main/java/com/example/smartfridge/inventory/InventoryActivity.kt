package com.example.smartfridge.inventory

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.R
import com.example.smartfridge.recipes.RecipeAdapter
import com.example.smartfridge.recipes.Recipes
import com.example.smartfridge.shoppinglists.ItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InventoryActivity : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var listViewInventory: ListView
    private lateinit var filterView: SearchView
    private lateinit var camViewButton: ImageButton
    private lateinit var inventoryViewModel: InventoryViewModel
    private lateinit var inventoryAdapter: ArrayAdapter<InventoryItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory)

        inventoryViewModel = ViewModelProviders.of(this, InventoryViewModelFactory.getInstance()).get(InventoryViewModel::class.java)

        listViewInventory = findViewById(R.id.listViewInventory)
        filterView = findViewById(R.id.filter_inventory)
        camViewButton = findViewById(R.id.camViewImage)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Inventory"
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        camViewButton.setOnClickListener{
            val camViewIntent = Intent(this, FridgeCamView::class.java)
            startActivity(camViewIntent)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val addInventoryItemIntent = Intent(this, AddInventoryItem::class.java)
            startActivityForResult(addInventoryItemIntent, ADD_ITEM_REQUEST)
        }

        listViewInventory.onItemClickListener = AdapterView.OnItemClickListener { _, _, item, _ ->
            updateQuantityDialog(item)
        }

        listViewInventory.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, item, _ ->
            deleteDialog(item)
            true
        }

        filterView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                inventoryAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                inventoryAdapter.filter.filter(newText)
                return false
            }
        })

    }

    override fun onStart() {
        super.onStart()

        inventoryAdapter = InventoryAdapter(this, inventoryViewModel.getInventory())
        listViewInventory.adapter = inventoryAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_ITEM_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            data.getStringExtra("name")?.let { inventoryViewModel.addItem(it,
                data.getStringExtra("quantity")!!, data.getStringExtra("expiration_date")!!
            ) }
        }

    }

    private fun updateQuantityDialog(item: Int) {

        val editTextView = EditText(this)
        editTextView.setText(inventoryViewModel.getQuantity(item))
        editTextView.hint = "Enter Quantity"
        editTextView.inputType = InputType.TYPE_CLASS_NUMBER

        val dialogBuilder = AlertDialog.Builder(this)
            .setEditText(editTextView)
            .setPositiveButton("Edit", DialogInterface.OnClickListener {
                    dialog, _ ->
                inventoryViewModel.editQuantity(item, editTextView.text.toString())
                val inventoryAdapter = InventoryAdapter(this, inventoryViewModel.getInventory())
                listViewInventory.adapter = inventoryAdapter
                dialog.dismiss()
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Edit Quantity")
        alert.show()

    }

    private fun deleteDialog(item: Int) {

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Do you want to delete?")

            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    dialog, _ -> inventoryViewModel.deleteItem(item)
                val inventoryAdapter = InventoryAdapter(this, inventoryViewModel.getInventory())
                listViewInventory.adapter = inventoryAdapter
                Toast.makeText(this, "Item deleted.", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Delete")
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.shopping_lists_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more_information -> {
                mDialog =
                    DialogFragmentInventoryActivity.newInstance()
                mDialog.show(supportFragmentManager,
                    TAG
                )
                true
            }
            R.id.delete_all_lists -> {
                inventoryAdapter = InventoryAdapter(this, inventoryViewModel.deleteAllitems())
                listViewInventory.adapter = inventoryAdapter
                Toast.makeText(this, "All items deleted.", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    val Float.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun AlertDialog.Builder.setEditText(editText: EditText): AlertDialog.Builder {
        val container = FrameLayout(context)
        container.addView(editText)
        val containerParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        val marginHorizontal = 48F
        val marginTop = 16F
        containerParams.topMargin = (marginTop / 2).toPx
        containerParams.leftMargin = marginHorizontal.toInt()
        containerParams.rightMargin = marginHorizontal.toInt()
        container.layoutParams = containerParams

        val superContainer = FrameLayout(context)
        superContainer.addView(container)

        setView(superContainer)

        return this
    }

    companion object {
        private const val TAG = "SmartFridge:InventoryActivity"
        private val ADD_ITEM_REQUEST = 0
    }
}