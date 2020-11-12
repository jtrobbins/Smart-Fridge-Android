package com.example.smartfridge.inventory

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.smartfridge.R
import com.example.smartfridge.recipes.Recipes

class InventoryAdapter (private val context: Activity, private var items: List<InventoryItem>) :
    ArrayAdapter<InventoryItem>(context, R.layout.inventory_item, items), Filterable {

    private var filteredInventoryList: List<InventoryItem> = items

    override fun getCount(): Int {
        return filteredInventoryList.size
    }

    override fun getItem(p0: Int): InventoryItem? {
        return filteredInventoryList[p0]
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem =
            inflater.inflate(R.layout.inventory_item, parent, false)

        val textViewName = listViewItem.findViewById<TextView>(R.id.textViewItemName)
        val textViewQuantity = listViewItem.findViewById<TextView>(R.id.textViewItemQuantity)
        val textViewExpiration = listViewItem.findViewById<TextView>(R.id.textViewItemExpiration)

        val listItem = filteredInventoryList[position]

        val quantity = "Quantity: " +  listItem.itemQuantity
        val expiration = "Expiration: " + listItem.itemDateExpiration

        textViewName.text = listItem.itemName
        textViewQuantity.text = quantity
        textViewExpiration.text = expiration

        return listViewItem
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredInventoryList = filterResults.values as List<InventoryItem>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    items
                else
                    items.filter {
                        it.itemName.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }

}