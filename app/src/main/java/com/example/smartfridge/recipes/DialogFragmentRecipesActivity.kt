package com.example.smartfridge.recipes

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogFragmentRecipesActivity : DialogFragment() {

    companion object {

        fun newInstance(): DialogFragmentRecipesActivity {
            return DialogFragmentRecipesActivity()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Recipes")
            .setMessage("Browse from our collection of amazing recipes. Filter your search by name or skill level.")
            .create()
    }
}