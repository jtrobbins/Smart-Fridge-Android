package com.example.smartfridge.inventory

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentAddInventoryItem : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentAddInventoryItem {
            return DialogFragmentAddInventoryItem()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Add Item")
            .setMessage("Lorem Ipsum")
            .create()
    }
}