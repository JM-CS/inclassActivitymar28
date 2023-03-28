package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    /*
    val timerHandler = Handler(Looper.getMainLooper()) {
        timerText.text = it.what.toString()
        true
    }

    val timerText: TextView by lazy {
        findViewById(R.id.TimerTextView)
    }
*/

    val timerText: TextView by lazy {
        findViewById<TextView>(R.id.TimerTextView)
    }
    val mrButton: Button by lazy {
        findViewById<Button>(R.id.button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = CoroutineScope(Job() + Dispatchers.Default)

        mrButton.setOnClickListener{
            scope.launch { countdownTimer() }
        }



        /*Thread {
            for (i in 100 downTo (1)) {
                Log.d("Countdown", i.toString())
                Thread.sleep(1000)
                timerText.text = i.toString()

                timerHandler.sendEmptyMessage(i)
            }
        }.start()
         */


    }
    suspend fun countdownTimer() {
        repeat(100) {
            (100 - it).toString().run{
                withContext(Dispatchers.Main){
                    timerText.text = this@run
                }
            }
            delay(1000)
        }
    }
}