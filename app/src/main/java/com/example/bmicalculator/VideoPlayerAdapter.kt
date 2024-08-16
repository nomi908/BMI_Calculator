package com.example.bmicalculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class VideoPlayerAdapter (val context: Context, val videolists: ArrayList<File>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.videoplayeradapter,  parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videolists.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val video = videolists[position]
        holder.bind(video.name)
    }



}


//
//class VideoPlayerAdapter(val context: Context, val videolist : ArrayList<String>) : RecyclerView.Adapter<MyViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.activity_video_players, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//       return videolist.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//    }
//
//}
////class VideoPlayerAdapter(val context: Context, val videolists: ArrayList<String>): RecyclerView.Adapter<MyViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//         val view = LayoutInflater.from(context).inflate(R.layout.videoplayeradapter, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return videolists.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//    }
//
//}