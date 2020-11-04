package com.example.smartfridge.recipes

import androidx.lifecycle.ViewModel
import com.example.smartfridge.shoppinglists.ShoppingList
import java.util.ArrayList

class RecipesViewModel : ViewModel() {

    private var lists : MutableList<Recipes> = ArrayList()

    fun addItem(name: String, level: String, servings: String, pTime: String, cTime: String) {
        val newItem = Recipes(name, level, servings, pTime, cTime)
        if(!lists.contains(newItem)) {
            lists.add(newItem)
        }

    }

    fun deleteItem(index: Int) {
        lists.removeAt(index)
    }

    fun getLists(): MutableList<Recipes> {
        return lists
    }

    fun clearAll(): MutableList<Recipes> {
        lists.clear()
        return lists
    }

    companion object {
        private var instance : RecipesViewModel? = null
        fun getInstance() =
            instance ?: synchronized(RecipesViewModel::class.java){
                instance ?: RecipesViewModel().also { instance = it }
            }
    }
}