package com.example.mytunes

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity()
{
    lateinit var button:Button
    lateinit var pause:Button
    lateinit var seekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.play)
        seekBar = findViewById(R.id.seekBar2)

        // MediaPlayer using local source

//        val mediaPlayer = MediaPlayer.create(this,R.raw.meredilkechain)
//        mediaPlayer.start()
//        button.setOnClickListener()
//        {
//            if(button.text == "Play")
//            {
//                mediaPlayer.pause()
//                button.text = "Pause"
//            }
//            else
//            {
//                mediaPlayer.start()
//                button.text = "Play"
//            }
//        }

        // MediaPlayer using web source

        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource("http://socialdance.stanford.edu/music/Durang_Lancers_3.m4a")

        // OnPreparedListener prepares link to play
        mediaPlayer.setOnPreparedListener()
        {
            Toast.makeText(this,"Music is playing..",Toast.LENGTH_SHORT).show()
            mediaPlayer.start()
            seekBar.max = mediaPlayer.duration //setting maximum value of seekbar because after this value music is not gonna play
            
            // we must have to override this all 3 methods otherwise giving error on object
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, i: Int, fromuser: Boolean) {
                    if(fromuser) {
                        mediaPlayer.seekTo(i)
                    }
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })
        }
        // prepareAsync is used to play live data over stream
        mediaPlayer.prepareAsync()
        button.setOnClickListener()
        {
            if(button.text == "Play")
            {
                mediaPlayer.pause()
                button.text = "Pause"
            }
            else
            {
                mediaPlayer.start()
                button.text = "Play"
            }
        }
    }
}