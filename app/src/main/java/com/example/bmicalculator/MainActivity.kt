package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var text:View ?= null
    var general:View ?= null
    var alltools:LinearLayout ?= null
    var bmipassing:CardView ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       text = findViewById(R.id.text)
       general = findViewById(R.id.general)
       alltools = findViewById<LinearLayout>(R.id.alltools)
       bmipassing = findViewById<CardView>(R.id.bmipass)

        text!!.setOnClickListener{
            intent = Intent(this, TextActivity::class.java)
            startActivity(intent)

        }

        general!!.setOnClickListener {
            intent = Intent(this, generalopens::class.java)
            startActivity(intent)
        }
        alltools!!.setOnClickListener{
            intent = Intent(this, All_Tools::class.java )
            startActivity(intent)
        }
        bmipassing!!.setOnClickListener {
            intent = Intent(this, bmicalculator::class.java)
            startActivity(intent)
        }


    }
}