package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class All_Tools : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_tools)
        val gomusic = findViewById<CardView>(R.id.gomusic)
        val videoplayer = findViewById<CardView>(R.id.videoplayer)
        val myallvideos = findViewById<CardView>(R.id.myvideosplayer)
        gomusic.setOnClickListener{
        intent = Intent(this, MusicHP::class.java)
            startActivity(intent)
        }
        videoplayer.setOnClickListener{
            intent = Intent(this, Video_Players::class.java)
            startActivity(intent)
        }
        myallvideos.setOnClickListener {
            intent = Intent(this, myvideos::class.java)
            startActivity(intent)
        }





    }
}