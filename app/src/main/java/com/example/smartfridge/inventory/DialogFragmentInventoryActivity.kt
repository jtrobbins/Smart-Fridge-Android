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
            .setMessage("Lorem Ipsum")
            .create()
    }
}