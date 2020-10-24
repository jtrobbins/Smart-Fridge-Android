package com.example.smartfridge.shoppinglists

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.smartfridge.R

class ItemList(private val context: Activity, private var items: List<Item>) :
    ArrayAdapter<Item>(context, R.layout.shopping_lists_view_names, items) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem =
            inflater.inflate(R.layout.shopping_lists_view_items, parent, false)

        val textViewName = listViewItem.findViewById<TextView>(R.id.textViewItemName)
        val textViewQuantity = listViewItem.findViewById<TextView>(R.id.textViewQuantity)

        val listItem = items[position]

        val quantity = "Quantity: " +  listItem.itemQuantity

        textViewName.text = listItem.itemName
        textViewQuantity.text = quantity

        return listViewItem
    }
}