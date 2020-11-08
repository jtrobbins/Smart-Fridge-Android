package com.example.smartfridge.inventory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R
import com.example.smartfridge.shoppinglists.DialogFragmentAddShoppingItem

class AddInventoryItem : AppCompatActivity() {
    private lateinit var mDialog: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_inventory_item)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add Item"
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)
        toolbar.setNavigationOnClickListener {
            val data = Intent()
            setResult(Activity.RESULT_CANCELED, data)
            finish()
        }

        var mNameText = findViewById<View>(R.id.inventory_item_title) as EditText
        var mQuantityText = findViewById<View>(R.id.inventory_item_quantity) as EditText
        var mExpirationText = findViewById<View>(R.id.inventory_item_expiration) as EditText

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            val data = Intent()
            setResult(Activity.RESULT_CANCELED, data)
            finish()
        }

        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener {
            mNameText.setText("")
            mQuantityText.setText("")
        }

        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {

            if (mNameText.text.toString() == "" || mQuantityText.text.toString() == "" || mExpirationText.text.toString() == "") {
                val data = Intent()
                setResult(Activity.RESULT_CANCELED, data)
                finish()
            }

            val data = Intent()

            data.putExtra("name", mNameText.text.toString())
            data.putExtra("quantity", mQuantityText.text.toString())
            data.putExtra("expiration", mExpirationText.text.toString())
            setResult(Activity.RESULT_OK, data)
            finish()

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
                    DialogFragmentAddShoppingItem.newInstance()
                mDialog.show(supportFragmentManager,
                    TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:InventoryActivity"
    }
}