package com.example.smartfridge.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InventoryListViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when {
                isAssignableFrom(InventoryListViewModel::class.java) -> InventoryListViewModel.getInstance()
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T


    companion object {
        private var instance : InventoryListViewModelFactory? = null
        fun getInstance() =
            instance ?: synchronized(InventoryListViewModelFactory::class.java){
                instance ?: InventoryListViewModelFactory().also { instance = it }
            }
    }
}