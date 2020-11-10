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
            .setTitle("Shopping List Contents")
            .setMessage("View the contents of your shopping list. Add a new item to your list using " +
                    "the button in the lower right corner. Mark items as obtained using the checkbox " +
                    "next to the item. Click an item to change the quantity or press and hold an item to delete. " +
                    "Delete all items in the list or change the name of your list using the drop down menu" +
                    " in the upper right corner.")
            .create()
    }
}