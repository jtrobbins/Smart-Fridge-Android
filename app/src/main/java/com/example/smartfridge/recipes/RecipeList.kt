package com.example.smartfridge.recipes

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.smartfridge.R

class RecipeList(private val context: Activity, private var recipeLists: List<Recipes>) :
    ArrayAdapter<Recipes>(context, R.layout.recipe_view_details, recipeLists) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.recipe_view_details, parent, false)

        val textViewRecipeName = listViewItem.findViewById<TextView>(R.id.textViewRecipeName)
        val textViewPrepTime = listViewItem.findViewById<TextView>(R.id.textViewRecipePrepTime)
        val textViewCookTime = listViewItem.findViewById<TextView>(R.id.textViewRecipeCookTime)
        val textViewRecipeServings = listViewItem.findViewById<TextView>(R.id.textViewRecipeServings)

        val listItem = recipeLists[position]

        textViewRecipeName.text = listItem.recipeName
        textViewPrepTime.text = listItem.recipeServings
        textViewCookTime.text = listItem.recipePrepTime
        textViewRecipeServings.text = listItem.recipeCookTime

        return listViewItem
    }
}