package com.example.smartfridge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<View>(R.id.button1) as Button
        button1.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

        val button2 = findViewById<View>(R.id.button2) as Button
        button2.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

        val button3 = findViewById<View>(R.id.button3) as Button
        button3.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

        val button4 = findViewById<View>(R.id.button4) as Button
        button4.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)

        }

        val button5 = findViewById<View>(R.id.button5) as Button
        button5.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

        val button6 = findViewById<View>(R.id.button6) as Button
        button6.setOnClickListener {
            val notImplementedIntent = Intent(this, NotImplementedActivity::class.java)
            startActivity(notImplementedIntent)
        }

    }
}