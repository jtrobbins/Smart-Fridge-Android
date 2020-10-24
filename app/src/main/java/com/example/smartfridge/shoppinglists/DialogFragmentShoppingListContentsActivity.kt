package com.example.smartfridge.shoppinglists

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentShoppingListContentsActivity : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentShoppingListContentsActivity {
            return DialogFragmentShoppingListContentsActivity()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Lorem Ipsum")
            .setMessage("Lorem Ipsum.")
            .create()
    }
}