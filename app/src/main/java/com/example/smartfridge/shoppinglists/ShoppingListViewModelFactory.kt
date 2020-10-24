package com.example.smartfridge.shoppinglists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingListViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when {
                isAssignableFrom(ShoppingListViewModel::class.java) -> ShoppingListViewModel.getInstance()
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T


    companion object {
        private var instance : ShoppingListViewModelFactory? = null
        fun getInstance() =
            instance ?: synchronized(ShoppingListViewModelFactory::class.java){
                instance ?: ShoppingListViewModelFactory().also { instance = it }
            }
    }
}