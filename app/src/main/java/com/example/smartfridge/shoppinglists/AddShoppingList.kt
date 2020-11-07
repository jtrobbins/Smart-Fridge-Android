package com.example.smartfridge.shoppinglists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R
import java.text.SimpleDateFormat
import java.util.*

class AddShoppingList : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private var iconSelected: Int = 1

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

        var icon1 = findViewById<View>(R.id.icon1) as ImageButton
        var icon2 = findViewById<View>(R.id.icon2) as ImageButton
        var icon3 = findViewById<View>(R.id.icon3) as ImageButton
        var icon4 = findViewById<View>(R.id.icon4) as ImageButton
        var icon5 = findViewById<View>(R.id.icon5) as ImageButton
        var icon6 = findViewById<View>(R.id.icon6) as ImageButton
        icon1.isSelected = true

        icon1.setOnClickListener {
            if (!icon1.isSelected) {
                icon1.isSelected = true
                icon2.isSelected = false
                icon3.isSelected = false
                icon4.isSelected = false
                icon5.isSelected = false
                icon6.isSelected = false

                iconSelected = 1
            }
        }

        icon2.setOnClickListener {
            if (!icon2.isSelected) {
                icon2.isSelected = true
                icon1.isSelected = false
                icon3.isSelected = false
                icon4.isSelected = false
                icon5.isSelected = false
                icon6.isSelected = false

                iconSelected = 2
            }
        }

        icon3.setOnClickListener {
            if (!icon3.isSelected) {
                icon3.isSelected = true
                icon1.isSelected = false
                icon2.isSelected = false
                icon4.isSelected = false
                icon5.isSelected = false
                icon6.isSelected = false

                iconSelected = 3
            }
        }

        icon4.setOnClickListener {
            if (!icon4.isSelected) {
                icon4.isSelected = true
                icon1.isSelected = false
                icon2.isSelected = false
                icon3.isSelected = false
                icon5.isSelected = false
                icon6.isSelected = false

                iconSelected = 4
            }
        }

        icon5.setOnClickListener {
            if (!icon5.isSelected) {
                icon5.isSelected = true
                icon1.isSelected = false
                icon2.isSelected = false
                icon3.isSelected = false
                icon4.isSelected = false
                icon6.isSelected = false

                iconSelected = 5
            }
        }

        icon6.setOnClickListener {
            if (!icon6.isSelected) {
                icon6.isSelected = true
                icon1.isSelected = false
                icon2.isSelected = false
                icon3.isSelected = false
                icon4.isSelected = false
                icon5.isSelected = false

                iconSelected = 6
            }
        }

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            val data = Intent()
            setResult(Activity.RESULT_CANCELED, data)
            finish()
        }

        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener {
            mTitleText.setText("")

            icon1.isSelected = true
            icon2.isSelected = false
            icon3.isSelected = false
            icon4.isSelected = false
            icon5.isSelected = false
            icon6.isSelected = false

            iconSelected = 1
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
            data.putExtra("icon", iconSelected.toString())

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