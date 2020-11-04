package com.example.smartfridge.recipes

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.smartfridge.R

class RecipeList(private val context: Activity, private var recipeLists: List<Recipes>) :
    ArrayAdapter<Recipes>(context, R.layout.recipe_view_details, recipeLists) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.recipe_view_details, parent, false)

        val textViewRecipeName = listViewItem.findViewById<TextView>(R.id.textViewRecipeName)
        val textViewRecipeLevel = listViewItem.findViewById<TextView>(R.id.textViewRecipeLevel)
        val textViewRecipeServings = listViewItem.findViewById<TextView>(R.id.textViewRecipeServings)
        val textViewPrepTime = listViewItem.findViewById<TextView>(R.id.textViewRecipePrepTime)
        val textViewCookTime = listViewItem.findViewById<TextView>(R.id.textViewRecipeCookTime)
        val imageViewImage = listViewItem.findViewById<ImageView>(R.id.listImageRecipe)

        val listItem = recipeLists[position]

        textViewRecipeName.text = listItem.recipeName
        textViewRecipeLevel.text = listItem.recipeLevel
        textViewRecipeServings.text = listItem.recipeServings
        textViewPrepTime.text = listItem.recipePrepTime
        textViewCookTime.text = listItem.recipeCookTime

        if (listItem.recipeName == "Broccoli Salad") {
            imageViewImage.setImageResource(R.drawable.broccoli_salad_circle)
        } else if (listItem.recipeName == "French Toast") {
            imageViewImage.setImageResource(R.drawable.french_toast_circle)
        } else if (listItem.recipeName == "Homemade Lasagna") {
            imageViewImage.setImageResource(R.drawable.homemade_lasagna_circle)
        } else if (listItem.recipeName == "Coconut Cake") {
            imageViewImage.setImageResource(R.drawable.coconut_cake_circle)
        } else if (listItem.recipeName == "Stuffed Bell Peppers") {
            imageViewImage.setImageResource(R.drawable.stuffed_bell_peppers_circle)
        }

        return listViewItem
    }
}