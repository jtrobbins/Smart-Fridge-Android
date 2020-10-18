package com.example.smartfridge

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentShoppingListsActivity : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentShoppingListsActivity {
            return DialogFragmentShoppingListsActivity()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Lorem Ipsum")
            .setMessage("Lorem Ipsum.")
            .create()
    }
}