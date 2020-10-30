package com.example.smartfridge.inventory

import androidx.lifecycle.ViewModel
import java.util.ArrayList

class InventoryListViewModel : ViewModel() {

    private var items : MutableList<InventoryItem> = ArrayList()

    fun addItem(name: String, quantity: String, expDate : String) {
        items.add(InventoryItem(name, quantity, expDate))
    }

    fun deleteItem(index: Int) {
        items.removeAt(index)
    }

    fun getItems(): MutableList<InventoryItem> {
        return items
    }

    fun deleteAllItems(): MutableList<InventoryItem> {
        items.clear()
        return items
    }


    fun getItemName(index: Int): String {
        return items[index].itemName
    }

    fun editItemName(index: Int, name: String)  {
        items[index].itemName = name
    }

    fun getQuantity(index: Int): String  {
        return items[index].itemQuantity
    }

    fun editQuantity(index: Int, quantity: Int)  {
        items[index].itemQuantity
    }

    companion object {
        private var instance : InventoryListViewModel? = null
        fun getInstance() =
            instance ?: synchronized(InventoryListViewModel::class.java){
                instance ?: InventoryListViewModel().also { instance = it }
            }
    }
}