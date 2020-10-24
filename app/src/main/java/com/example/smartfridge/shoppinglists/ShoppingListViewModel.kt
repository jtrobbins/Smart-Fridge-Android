package com.example.smartfridge.shoppinglists

import androidx.lifecycle.ViewModel
import java.util.ArrayList

class ShoppingListViewModel : ViewModel() {

    private var lists : MutableList<ShoppingList> = ArrayList()

    fun addLists(name: String, description: String) {
        lists.add(ShoppingList(name, description))
    }

    fun getLists(): MutableList<ShoppingList> {
        return lists
    }
}