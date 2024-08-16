package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class generalopens : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generalopens)
        var counter = findViewById<LinearLayout>(R.id.counterApp)
        counter.setOnClickListener{
            intent = Intent(this, CounterHM::class.java)
            startActivity(intent)
        }

    }
}