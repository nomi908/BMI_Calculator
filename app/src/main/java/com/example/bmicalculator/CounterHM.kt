package com.example.bmicalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class CounterHM : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_counter_hm)
        val counterview = findViewById<TextView>(R.id.counterview)
        val addbtn = findViewById<CardView>(R.id.addbtn)
        var counter1 = 0
        val subbtn = findViewById<CardView>(R.id.subbtn)
        counterview.text.toString()

        addbtn.setOnClickListener{
            counter1++

            counterview.setText(counter1.toString())
        }

        subbtn.setOnClickListener{
            counter1--
            counterview.setText(counter1.toString())
        }





    }
}