package com.example.bmicalculator

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
//import androidx.activity.ImmLeaksCleaner.FailedInitialization.servedView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class RecyclerViewAdapter(val context: Activity, val musiclist: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    val mp = MediaPlayer()

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listitems = itemView.findViewById<CardView>(R.id.listiems)
        val imageView = itemView.findViewById<ImageView>(R.id.image_view)
        val textView = itemView.findViewById<TextView>(R.id.Text_view)
        val player = itemView.findViewById<Button>(R.id.playinghandler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return musiclist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val file = File(musiclist.get(position))
        holder.textView.setText(file.name)

        holder.listitems.setOnClickListener{
            if (mp == null) {
//                mp = MediaPlayer()
                mp.setDataSource(musiclist.get(position))
                mp.prepare()
                mp.start()
                holder.player.setText("Stop")

            } else {
                if (mp.isPlaying) {
                    mp.stop()
                    mp.reset()
                    holder.player.setText("Play")

                }
            mp.setDataSource(musiclist.get(position))
            mp.prepare()
            mp.start()
        }





}



}

}

