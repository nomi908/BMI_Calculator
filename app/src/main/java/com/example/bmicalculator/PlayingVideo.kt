package com.example.bmicalculator

import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class PlayingVideo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.playingvideo)
        val videoview = findViewById<VideoView>(R.id.videoview)

        // Retrieve video path from intent extras
        val videoPath = intent.getStringExtra("name")
        if (videoPath != null) {
            // Use the videoPath as needed (e.g., play the video)
            // Set video path
            Log.d("videopath", "VideoPath: $videoPath")

            videoview.setVideoPath(videoPath)

            // Create MediaController for play, pause, etc.
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoview)
            videoview.setMediaController(mediaController)

            // Start playing video
            videoview.start()

        }else {
            Log.e("PlayingVideo", "VideoPath or VideoView is null")
        }
    }




}