package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import com.example.stopwatch.MainActivity.Companion.STATE_TIME

class MainActivity : AppCompatActivity() {

    //make a class-wide static constant in Kotlin
    companion object{
        //all your static constants go here
        val TAG = "MainActivity"

        val STATE_TIME = "display time"

    }

    lateinit var buttonStart : Button
    lateinit var buttonRestart: Button
    lateinit var chronometer: Chronometer
    var running = false
    var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()

        if(savedInstanceState != null){
            time = savedInstanceState.getLong(STATE_TIME)
            chronometer.base = SystemClock.elapsedRealtime() - time
            if(!running) {
                chronometer.base = SystemClock.elapsedRealtime()-time
                chronometer.start()
                buttonStart.text = "STOP"
                buttonStart.setBackgroundColor(Color.RED)
            }
            else{
                chronometer.stop()
                buttonStart.text = "START"
                buttonStart.setBackgroundColor(Color.rgb(54, 191,90))
                time = SystemClock.elapsedRealtime() - chronometer.base
            }
            running = !running
            time = savedInstanceState.getLong(STATE_TIME)
        }

        buttonStart.setOnClickListener {
            if(!running) {
                chronometer.base = SystemClock.elapsedRealtime()-time
                chronometer.start()
                buttonStart.text = "STOP"
                buttonStart.setBackgroundColor(Color.RED)
            }
            else{
                chronometer.stop()
                buttonStart.text = "START"
                buttonStart.setBackgroundColor(Color.rgb(54, 191,90))
                time = SystemClock.elapsedRealtime() - chronometer.base
            }
            running = !running
        }
        buttonRestart.setOnClickListener {
            chronometer.stop()
            buttonStart.text = "START"
            buttonStart.setBackgroundColor(Color.rgb(54, 191, 90))
            chronometer.base = SystemClock.elapsedRealtime() + time
        }
    }

    //preserve state through orientation changes
    override fun onSaveInstanceState(outState: Bundle) {
        //calculate display time if currently running
        if(running){
            time = SystemClock.elapsedRealtime()- chronometer.base
        }

        //save key-value pairs to the bundle before the superclass call
        outState.putLong(STATE_TIME, time)

        super.onSaveInstanceState(outState)
    }

    private fun wireWidgets() {
        buttonStart = findViewById(R.id.button_main_start)
        buttonRestart = findViewById(R.id.button_main_restart)
        chronometer = findViewById(R.id.chronometer_main_stopwatch)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }
}