package com.example.bmicalculator

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class Video_Players : AppCompatActivity() {
    private val REQUEST_EXTERNAL_STORAGE_PERMISSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video_players)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            setRecyclerView()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_EXTERNAL_STORAGE_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_EXTERNAL_STORAGE_PERMISSION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
            setRecyclerView()
        }


    }

    fun getAllVideos(directoryPath: String): ArrayList<File> {
        val videoFiles = ArrayList<File>()

        // Create a File object for the directory
        val directory = File(directoryPath)

        // Check if the directory exists and is a directory
        if (directory.exists() && directory.isDirectory) {
            Log.d("get_videos", "Directory exist")
            // List all files in the directory
            val files = directory.listFiles()
            Log.d("get_videos", "files size: "+files.size)


            // Iterate over the files
            files?.forEach { file ->
                // Check if the file is a video file
                Log.d("get_videos", "current file in loop: "+file.path)

                if (file.isFile && isVideoFile(file)) {
                    // Add the video file to the list\
                    Log.d("get_videos", "adding file: "+file.path)

                    videoFiles.add(file)
                }
                else{
                    videoFiles.addAll(getAllVideos(file.absolutePath))
                }
            }
        }

        return videoFiles
    }
    fun isVideoFile(file: File): Boolean {
        // Check if the file has a video extension (you can customize this based on your requirements)
        val videoExtensions = arrayOf(
            "mp4", "mkv", "avi", "mov", "wmv", "flv", "3gp", // Common video formats
            "m4v", "webm", "mpeg", "mpg", "m2v", "mpe", "ts", // Additional video formats
            "asf", "divx", "dat", "vob", "mod", "tod", // Older video formats
            "xvid", "ogv", "ogm", "ogx", "mts", "m2ts", // Less common video formats
            "rm", "rmvb", "ram", "qt", // Older and less common formats
            "amv", "dvr-ms", "evo", "m2p", "mp2v", "m1v" // Rare and specialized formats
        )
        val extension = file.extension.toLowerCase()
        return videoExtensions.contains(extension)
    }


    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.videoplRecyclerview)
        recyclerView?.layoutManager = GridLayoutManager(this,3)
        Log.e("bold1", "path: " + Environment.getExternalStorageDirectory().toString(), )
        val videolist = getAllVideos(Environment.getExternalStorageDirectory().toString())
        Log.e("bold", "setRecyclerView: " + videolist.size )
        val adapter = VideoPlayerAdapter(this, videolist)
        recyclerView?.adapter = adapter
    }



}
