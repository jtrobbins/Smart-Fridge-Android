package com.example.smartfridge.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentMainActivity : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentMainActivity {
            return DialogFragmentMainActivity()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Smart Fridge")
            .setMessage("Welcome to Smart Fridge! Check out our features by clicking the buttons" +
                    " below.")
            .create()
    }
}