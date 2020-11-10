package com.example.smartfridge.shoppinglists

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
            .setTitle("Shopping Lists")
            .setMessage("Scroll through your list of shopping lists. Create a new list using the button in the lower " +
                    "right corner. Tap a list to view its contents or press and hold a list to delete. Delete All lists " +
                    "using the Delete All button within the menu located in the upper right corner of the screen.")
            .create()
    }
}