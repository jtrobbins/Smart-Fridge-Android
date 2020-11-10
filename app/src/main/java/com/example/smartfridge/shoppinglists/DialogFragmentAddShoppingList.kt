package com.example.smartfridge.shoppinglists

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentAddShoppingList : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentAddShoppingList {
            return DialogFragmentAddShoppingList()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Add Shopping List")
            .setMessage("Create a shopping list. Provide a icon and a title for your list. Choose from a selection of icons to help personalize your list.")
            .create()
    }
}