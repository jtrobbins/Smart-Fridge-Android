package com.example.smartfridge.recipes

import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.smartfridge.R

class FrenchToastActivity : AppCompatActivity() {

    private lateinit var recipeRatingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_french_toast)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "French Toast"
        toolbar.setTitleTextAppearance(this, R.style.AppTheme_AppBarOverlayMain)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        recipeRatingBar = findViewById<RatingBar>(R.id.ratingBar)

        recipeRatingBar?.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                recipeRatingBar.rating = rating
                Toast.makeText(this, "Rating Submitted", Toast.LENGTH_SHORT).show()
            }

    }

    companion object {
        private const val TAG = "SmartFridge:RecipesActivity:FrenchToast"
    }
}