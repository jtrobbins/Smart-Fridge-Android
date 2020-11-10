package com.example.smartfridge.shoppinglists

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentAddShoppingItem : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentAddShoppingItem {
            return DialogFragmentAddShoppingItem()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Add Item")
            .setMessage("Add an item to your list. Provide a name and a quantity for your item.")
            .create()
    }
}