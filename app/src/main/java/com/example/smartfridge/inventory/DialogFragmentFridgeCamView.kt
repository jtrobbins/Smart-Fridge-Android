package com.example.smartfridge.inventory

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentFridgeCamView : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentFridgeCamView {
            return DialogFragmentFridgeCamView()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("FridgeCam View")
            .setMessage("View the inside of your fridge.")
            .create()
    }
}