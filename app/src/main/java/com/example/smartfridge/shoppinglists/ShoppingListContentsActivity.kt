package com.example.smartfridge.shoppinglists

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.smartfridge.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


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
        supportActionBar?.title = shoppingItemViewModel.getListName(intent.getIntExtra("ID", 0))
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab2)
        fab.setOnClickListener {
            val addShoppingItemIntent = Intent(this, AddShoppingItem::class.java)
            startActivityForResult(addShoppingItemIntent, ADD_LIST_REQUEST)
        }

        val imageViewIcon = findViewById<ImageView>(R.id.listContentsListImage)
        val listId = intent.getIntExtra("ID", 0)
        val icon = shoppingItemViewModel.getListIcon(listId)

        if (icon == 1) {
            imageViewIcon.setImageResource(R.drawable.ic_cutlery_icon)
        } else if (icon == 2) {
            imageViewIcon.setImageResource(R.drawable.ic_cake_icon)
        } else if (icon == 3) {
            imageViewIcon.setImageResource(R.drawable.ic_burger_icon)
        } else if (icon == 4) {
            imageViewIcon.setImageResource(R.drawable.ic_chicken_icon)
        } else if (icon == 5) {
            imageViewIcon.setImageResource(R.drawable.ic_strawberry_icon)
        } else if (icon == 6) {
            imageViewIcon.setImageResource(R.drawable.ic_milk_icon)
        }


        listViewLists.onItemClickListener = AdapterView.OnItemClickListener { _, _, item, _ ->
            updateQuantityDialog(item)
        }

       listViewLists.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, item, _ ->
           deleteDialog(item)
           true
       }

    }

    private fun updateQuantityDialog(item: Int) {

        val editTextView = EditText(this)
        val listId = intent.getIntExtra("ID", 0)
        editTextView.setText(shoppingItemViewModel.getQuantity(listId, item))
        editTextView.hint = "Enter Quantity"
        editTextView.inputType = TYPE_CLASS_NUMBER

        val dialogBuilder = AlertDialog.Builder(this)
            .setEditText(editTextView)
            .setPositiveButton("Edit", DialogInterface.OnClickListener {
                    dialog, _ ->
                shoppingItemViewModel.editQuantity(listId, item, editTextView.text.toString())
                val titleAdapter = ItemAdapter(this, shoppingItemViewModel.getItems(listId))
                listViewLists.adapter = titleAdapter
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
        val listId = intent.getIntExtra("ID", 0)

        dialogBuilder.setMessage("Do you want to delete?")

            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    dialog, _ -> shoppingItemViewModel.deleteItems(listId, item)
                val titleAdapter = ItemAdapter(this, shoppingItemViewModel.getItems(listId))
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

    private fun editNameDialog(item: Int) {

        val editTextView = EditText(this)
        editTextView.setText(shoppingItemViewModel.getListName(item))
        editTextView.hint = "Enter Title"

        val dialogBuilder = AlertDialog.Builder(this)
            .setEditText(editTextView)
            .setPositiveButton("Edit", DialogInterface.OnClickListener {
                    dialog, _ ->
                shoppingItemViewModel.editListName(item, editTextView.text.toString())
                supportActionBar?.title =  shoppingItemViewModel.getListName(item)
                dialog.dismiss()
            })

            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Edit Name")
        alert.show()
    }

    override fun onStart() {
        super.onStart()

        val listId = intent.getIntExtra("ID", 0)
        val itemAdapter = ItemAdapter(this, shoppingItemViewModel.getItems(listId))
        listViewLists.adapter = itemAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_LIST_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val listId = intent.getIntExtra("ID", 0)
            data.getStringExtra("name")?.let {
                shoppingItemViewModel.addItems(listId, it, data.getStringExtra("quantity")!!, false
                )
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.shopping_lists_items_menu, menu)
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
            R.id.delete_all_items -> {
                val listId = intent.getIntExtra("ID", 0)
                val itemAdapter = ItemAdapter(this, shoppingItemViewModel.deleteAllItems(listId))
                listViewLists.adapter = itemAdapter
                true
            }
            R.id.edit_list_name -> {
                val listId = intent.getIntExtra("ID", 0)
                editNameDialog(listId)
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
        private const val TAG = "SmartFridge:ShoppingListContentsActivity"
        private val ADD_LIST_REQUEST = 0
    }
}