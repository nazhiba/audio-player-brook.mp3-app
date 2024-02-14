package com.nazhiba.audio_video

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var seekbar:SeekBar
    private lateinit var runnable: Runnable
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekbar = findViewById(R.id.progres)
        handler = Handler(Looper.getMainLooper())

        val playButton = findViewById<FloatingActionButton>(R.id.fabPLay)
        playButton.setOnClickListener(){
            if (mediaPlayer == null ){
                mediaPlayer = MediaPlayer.create(this, R.raw.brook)
                inisialisasiseekbar()
            }
            mediaPlayer?.start()
        }

        val pausebutton = findViewById<FloatingActionButton>(R.id.fabPause)
        pausebutton.setOnClickListener(){
            mediaPlayer?.pause()
        }

        val stopbutton = findViewById<FloatingActionButton>(R.id.fabStop)
        stopbutton.setOnClickListener(){
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer= null
            handler.removeCallbacks(runnable)
            seekbar.progress = 0
        }
    }
    private fun inisialisasiseekbar(){
        seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

        })
        val tvplayed = findViewById<TextView>(R.id.played)
        val tvdue = findViewById<TextView>(R.id.due)

        seekbar.max = mediaPlayer!!.duration
        runnable = Runnable{
            seekbar.progress = mediaPlayer!!.currentPosition

            val playtime = mediaPlayer!!.currentPosition/1000
            tvplayed.text = "$playtime sec"
            val duration = mediaPlayer!!.duration/1000
            val duetime = duration-playtime
            tvdue.text = "$duetime sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

    }
}