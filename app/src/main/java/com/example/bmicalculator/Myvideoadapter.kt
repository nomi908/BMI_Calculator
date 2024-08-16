package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.coroutineContext

class Myvideoadapter (val context: Context, val myallvideos : ArrayList<String>): RecyclerView.Adapter<Myvideoadapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myvideoadapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.myvideoadapter, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val allvideos = myallvideos[position]
        holder.bind(context,allvideos)
    }

    override fun getItemCount(): Int {
        return myallvideos.size
    }

    class MyViewHolder(itemview:View) : RecyclerView.ViewHolder(itemview){
    val videolists : CardView = itemview.findViewById<CardView>(R.id.myvideoadapter)
        val videoduration : TextView = itemview.findViewById<TextView>(R.id.videotime)
        val videoimage = itemview.findViewById<ImageView>(R.id.videothumbnails)
        val videoName = itemview.findViewById<TextView>(R.id.videoNaam)




        fun bind(context: Context,video: String) {
            val file = File(video)

            videolists.setOnClickListener {
                val intent = Intent(itemView.context, PlayingVideo::class.java)
                intent.putExtra("name", video)
                itemView.context.startActivity(intent)
            }
//            val retriever = MediaMetadataRetriever()
            Glide.with(context).load(video).into(videoimage)

            // Asynchronously retrieve and display video duration
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val duration = getVideoDuration(video)
                    val videonaam = getVideoName(video)

                    // Update UI on the Main thread
                    withContext(Dispatchers.Main) {
                        // Format duration into minutes and seconds
                        val minutes = duration / 1000 / 60
                        val seconds = duration / 1000 % 60

                        videoduration.text = String.format("%02d:%02d", minutes, seconds)
                        videoName.text = String.format(("$videonaam"))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Handle error if needed
                }
            }





        }



        // Function to retrieve video duration
        private fun getVideoDuration(videoPath: String): Long {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(videoPath)
            val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            retriever.release()
            return durationStr?.toLong() ?: 0
        }
        // Function to retrieve video file name asynchronously
        private suspend fun getVideoName(videoPath: String): String {
            return withContext(Dispatchers.IO) {
                val file = File(videoPath)
                file.name
            }

        }


        }

    }











