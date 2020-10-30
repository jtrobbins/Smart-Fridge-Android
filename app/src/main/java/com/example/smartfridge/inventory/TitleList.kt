package com.example.smartfridge.inventory

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.smartfridge.R
import java.text.SimpleDateFormat
import java.util.*

class TitleList(private val context: Activity, private var inventoryList: List<InventoryItem>) :
    ArrayAdapter<InventoryItem>(context, R.layout.inventory_list_view_names, inventoryList) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem =
            inflater.inflate(R.layout.inventory_list_view_names, parent, false)

        val textViewName = listViewItem.findViewById<TextView>(R.id.textViewName)
        val textViewNumItems = listViewItem.findViewById<TextView>(R.id.textViewNumItems)
        val textViewExpDate=  listViewItem.findViewById<TextView>(R.id.textViewExpDate)

        val listItem = inventoryList[position]

        textViewName.text = listItem.itemName
        textViewNumItems.text = listItem.itemQuantity.toString()
        textViewExpDate.text = listItem.itemExpDate

        return listViewItem
    }
}