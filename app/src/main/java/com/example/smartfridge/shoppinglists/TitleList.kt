package com.example.smartfridge.shoppinglists

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.smartfridge.R
import java.text.SimpleDateFormat
import java.util.*

class TitleList(private val context: Activity, private var shoppingLists: List<ShoppingList>) :
    ArrayAdapter<ShoppingList>(context, R.layout.shopping_lists_view_names, shoppingLists) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem =
            inflater.inflate(R.layout.shopping_lists_view_names, parent, false)

        val textViewName = listViewItem.findViewById<TextView>(R.id.textViewName)
        val textViewNumItems = listViewItem.findViewById<TextView>(R.id.textViewNumItems)
        val textDateCreated =  listViewItem.findViewById<TextView>(R.id.textViewDateCreated)
        val imageViewIcon = listViewItem.findViewById<ImageView>(R.id.listImage)

        val listItem = shoppingLists[position]

        val date = "Created: " +  listItem.listDate
        val numItems = listItem.listItems.size.toString() + " items"
        val icon = listItem.listImage

        textViewName.text = listItem.listName
        textViewNumItems.text =  numItems
        textDateCreated.text = date

        if (icon == 1) {
            imageViewIcon.setImageResource(R.drawable.ic_shopping_list_icon)
        } else if (icon == 2) {
            imageViewIcon.setImageResource(R.drawable.ic_shopping_list_icon)
        } else if (icon == 3) {
            imageViewIcon.setImageResource(R.drawable.ic_shopping_list_icon)
        } else if (icon == 4) {
            imageViewIcon.setImageResource(R.drawable.ic_shopping_list_icon)
        } else if (icon == 5) {
            imageViewIcon.setImageResource(R.drawable.ic_shopping_list_icon)
        } else if (icon == 6) {
            imageViewIcon.setImageResource(R.drawable.ic_shopping_list_icon)
        }

        return listViewItem
    }
}