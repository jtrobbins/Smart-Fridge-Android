package com.example.smartfridge.shoppinglists

import java.util.ArrayList

data class ShoppingList (var listName: String = "", val listDate: String = "",
                         val listItems: MutableList<Item> = ArrayList())