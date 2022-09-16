package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    private lateinit var startButton : Button
    private lateinit var resetButton : Button
    private lateinit var chronometer: Chronometer
    var isStart = false
    var currentTime : Long = 0
    companion object{
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        wireWidgets()
        startButton.setOnClickListener {
            if(!isStart){
                startChronometer()
                chronometer.setBase(SystemClock.elapsedRealtime() - currentTime)
                startButton.setBackgroundColor(Color.rgb(255, 0, 0))
                startButton.text = "STOP"
            }
            else{
                startButton.setBackgroundColor(Color.rgb(0, 255, 0))
                currentTime =SystemClock.elapsedRealtime() - chronometer.base
                chronometer.stop()
                startButton.text = "START"
                isStart = false
            }
        }
        resetButton.setOnClickListener {
            if(isStart){
                currentTime = SystemClock.elapsedRealtime()
                chronometer.setBase(currentTime)
                startButton.setBackgroundColor(Color.rgb(255, 0, 0))
                startButton.text = "Stop"
            }
            else{
                currentTime = SystemClock.elapsedRealtime()
                chronometer.setBase(currentTime)
                if(!isStart) {
                    startChronometer()
                }
                startButton.setBackgroundColor(Color.rgb(0, 255, 0))
                startButton.text = "Start"
            }
        }
    }
    private fun startChronometer(){
        chronometer.start()
        startButton.text = "STOP"
        isStart = true
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
    private fun wireWidgets(){
        startButton = findViewById(R.id.button_main_start)
        resetButton = findViewById(R.id.button_main_reset)
        chronometer = findViewById(R.id.chronometer_main_stopwatch)
    }
}