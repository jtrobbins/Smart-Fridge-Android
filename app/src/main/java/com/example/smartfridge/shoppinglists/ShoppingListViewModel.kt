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
}