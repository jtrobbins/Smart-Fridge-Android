package com.example.smartfridge.shoppinglists

import androidx.lifecycle.ViewModel
import java.util.ArrayList

class ShoppingListViewModel : ViewModel() {

    private var lists : MutableList<ShoppingList> = ArrayList()

    fun addLists(name: String, date: String) {
        lists.add(ShoppingList(name, date))
    }

    fun getLists(): MutableList<ShoppingList> {
        return lists
    }

    fun addItems(index: Int, name: String, quantity: String) {
        lists[index].listItems.add(Item(name, quantity))
    }

    fun getItems(index: Int): MutableList<Item> {
        return lists[index].listItems
    }

    companion object {
        private var instance : ShoppingListViewModel? = null
        fun getInstance() =
            instance ?: synchronized(ShoppingListViewModel::class.java){
                instance ?: ShoppingListViewModel().also { instance = it }
            }
    }
}