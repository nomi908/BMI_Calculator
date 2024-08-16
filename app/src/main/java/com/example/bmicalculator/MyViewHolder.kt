package com.example.bmicalculator

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(view: View ) : RecyclerViewAdapter.MyViewHolder(view) {


    val videolists = view.findViewById<CardView>(R.id.videolists)
     val videoduration = view.findViewById<TextView>(R.id.videoduration)

    fun bind(video: String) {
        videoduration.setText(video)
    }
}
