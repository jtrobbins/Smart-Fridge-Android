package com.example.smartfridge.recipes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.example.smartfridge.R

class BroccoliSaladActivity : AppCompatActivity() {

    private lateinit var recipeRatingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_broccoli_salad)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Broccoli Salad"
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
        private const val TAG = "SmartFridge:RecipesActivity:BroccoliSalad"
    }
}