package com.example.smartfridge.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecipesViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when {
                isAssignableFrom(RecipesViewModel::class.java) -> RecipesViewModel.getInstance()
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T


    companion object {
        private var instance : RecipesViewModelFactory? = null
        fun getInstance() =
            instance ?: synchronized(RecipesViewModelFactory::class.java){
                instance ?: RecipesViewModelFactory().also { instance = it }
            }
    }
}