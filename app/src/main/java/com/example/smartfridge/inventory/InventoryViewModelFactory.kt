package com.example.smartfridge.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartfridge.shoppinglists.ShoppingListViewModel
import com.example.smartfridge.shoppinglists.ShoppingListViewModelFactory

class InventoryViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when {
                isAssignableFrom(InventoryViewModel::class.java) -> InventoryViewModel.getInstance()
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T


    companion object {
        private var instance : InventoryViewModelFactory? = null
        fun getInstance() =
            instance ?: synchronized(InventoryViewModelFactory::class.java){
                instance ?: InventoryViewModelFactory().also { instance = it }
            }
    }
}