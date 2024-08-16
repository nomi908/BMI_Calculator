package com.example.bmicalculator

import android.Manifest
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.PermissionRequest
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

var mediaPlayer: MediaPlayer? = null

private val Nothing?.isPlaying: Boolean
    get() = mediaPlayer?.isPlaying ?: false

class MusicHP : AppCompatActivity() {
    private val REQUEST_EXTERNAL_STORAGE_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_music_hp)
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
        if (requestCode == REQUEST_EXTERNAL_STORAGE_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setRecyclerView()
        }
    }

    private fun getAllMusicPaths(context: Context): ArrayList<String> {
        val musicPaths = ArrayList<String>()
        val contentResolver: ContentResolver = context.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Audio.Media.DATA)
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        val cursor: Cursor? = contentResolver.query(uri, projection, selection, null, sortOrder)

        cursor?.use { c ->
            val dataIndex = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            while (c.moveToNext()) {
                val path = c.getString(dataIndex)
                Log.d("Path", "path:  "+path)
                musicPaths.add(path)
            }
        }

        cursor?.close()
        return musicPaths
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.getmusic)
        val musicList = getAllMusicPaths(this)
        val adapter = RecyclerViewAdapter(this, musicList)
        recyclerView.adapter = adapter
    }

    companion object {
        var mediaPlayer: MediaPlayer? = null
    }


}
