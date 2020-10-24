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
            .setTitle("Lorem Ipsum")
            .setMessage("Lorem Ipsum.")
            .create()
    }
}