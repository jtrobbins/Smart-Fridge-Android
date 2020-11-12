package com.example.smartfridge.inventory

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentInventoryActivity : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentInventoryActivity {
            return DialogFragmentInventoryActivity()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Inventory")
            .setMessage("View the contents of your fridge. Add new items by clicking the button " +
                    "in the lower right corner. Click an item to change the quantity or press and hold to delete. " +
                    "Delete all items in your inventory by using the Delete All button from the drop down menu " +
                    "in the upper right corner.")
            .create()
    }
}