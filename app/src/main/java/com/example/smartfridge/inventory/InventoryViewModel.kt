package com.example.smartfridge.inventory

import androidx.lifecycle.ViewModel
import java.util.ArrayList

class InventoryViewModel : ViewModel() {

    private var lists : MutableList<InventoryItem> = ArrayList()

    fun addItem(name: String, quantity: String, expiration: String) {
        lists.add(InventoryItem(name, quantity, expiration))
    }

    fun deleteItem(index: Int) {
        lists.removeAt(index)
    }

    fun getInventory(): MutableList<InventoryItem> {
        return lists
    }

    fun getQuantity(index: Int): String  {
        return lists[index].itemQuantity
    }

    fun editQuantity(index: Int, quantity: String)  {
        lists[index].itemQuantity = quantity
    }

    companion object {
        private var instance : InventoryViewModel? = null
        fun getInstance() =
            instance ?: synchronized(InventoryViewModel::class.java){
                instance ?: InventoryViewModel().also { instance = it }
            }
    }
}