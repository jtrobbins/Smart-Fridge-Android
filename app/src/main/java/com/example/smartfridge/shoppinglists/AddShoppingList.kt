package com.example.smartfridge.shoppinglists

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
import java.text.SimpleDateFormat
import java.util.*

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
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        toolbar.setNavigationOnClickListener {
            val data = Intent()
            setResult(Activity.RESULT_CANCELED, data)
            finish()
        }

        var mTitleText = findViewById<View>(R.id.shopping_list_title) as EditText

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            val data = Intent()
            setResult(Activity.RESULT_CANCELED, data)
            finish()
        }

        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener {
            mTitleText.setText("")
        }

        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {

            if (mTitleText.text.toString() == "") {
                val data = Intent()
                setResult(Activity.RESULT_CANCELED, data)
                finish()
            }

            val data = Intent()

            val cal = Calendar.getInstance()
            val date = SimpleDateFormat("MMM d, yyyy").format(cal.time)

            data.putExtra("title", mTitleText.text.toString())
            data.putExtra("date", date)

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
                    DialogFragmentAddShoppingList.newInstance()
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
    }
}