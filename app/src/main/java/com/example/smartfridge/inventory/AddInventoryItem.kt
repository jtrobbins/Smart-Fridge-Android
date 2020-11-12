package com.example.smartfridge.inventory

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R
import java.text.SimpleDateFormat
import java.util.*

class AddInventoryItem : AppCompatActivity() {

    private lateinit var mDialog: DialogFragment
    private lateinit var mExpirationDate: EditText

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

        var mNameText = findViewById<View>(R.id.inventory_title) as EditText
        var mQuantityText = findViewById<View>(R.id.inventory_quantity) as EditText
        mExpirationDate = findViewById<View>(R.id.inventory_expiration) as EditText

        val foodGroupStaticSpinner = findViewById<View>(R.id.foodGroup_static_spinner) as Spinner
        val foodGroupStaticAdapter = ArrayAdapter
            .createFromResource(this, R.array.foodGroup_array,
                android.R.layout.simple_spinner_item)
        foodGroupStaticAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        foodGroupStaticSpinner.adapter = foodGroupStaticAdapter

        val locationStaticSpinner = findViewById<View>(R.id.location_static_spinner) as Spinner
        val locationStaticAdapter = ArrayAdapter
            .createFromResource(this, R.array.location_array,
                android.R.layout.simple_spinner_item)
        locationStaticAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationStaticSpinner.adapter = locationStaticAdapter

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            mExpirationDate.setText(SimpleDateFormat("MM/dd/yyyy").format(cal.time))

        }

        mExpirationDate.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

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
            mExpirationDate.setText("")
        }

        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {

            if (mNameText.text.toString() == "" || mQuantityText.text.toString() == "" ||
                    mExpirationDate.text.toString() == "") {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_LONG).show()
            } else {
                val data = Intent()

                data.putExtra("name", mNameText.text.toString())
                data.putExtra("quantity", mQuantityText.text.toString())
                data.putExtra("expiration_date", mExpirationDate.text.toString())

                Toast.makeText(this, "Submit successful.", Toast.LENGTH_LONG).show()

                setResult(Activity.RESULT_OK, data)
                finish()
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
                    DialogFragmentAddInventoryItem.newInstance()
                mDialog.show(supportFragmentManager,
                    TAG
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "SmartFridge:AddInventoryItemActivity"
    }
}