package com.example.smartfridge

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment

class AddShoppingList : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_shopping_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add Shopping List"

        var mTitleText = findViewById<View>(R.id.shopping_list_title) as EditText
        var mDescriptionText = findViewById<View>(R.id.shopping_list_description) as EditText

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            val data = Intent()
            setResult(Activity.RESULT_CANCELED, data)
            finish()
        }

        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener {
            mTitleText.setText("")
            mDescriptionText.setText("")
        }

        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more_information -> {
                mDialog = DialogFragmentAddShoppingList.newInstance()
                mDialog.show(supportFragmentManager, TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:ShoppingListsActivity"
    }
}