package com.nazhiba.audio_video

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this, R.raw.brook)

//        val button = findViewById<Button>(R.id.button)
//        button.setOnClickListener(){
//            mediaPlayer.start()
//        }
    }
}