package com.example.myapplication_stop_watch

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var timerTask : Timer? = null
    private var lap = 1
    private var isRunning = false


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playFLB.setOnClickListener {
            isRunning = !isRunning

            if (isRunning)
                start()
            else
                pause()
        }

        resetFLB.setOnClickListener {
            reset()
        }

        labBtn.setOnClickListener {
            RecordLapTime()
        }


    }//onCreate END

    private fun start(){
        playFLB.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = kotlin.concurrent.timer(period = 10){
            time++

            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                secText.text = "$sec"
                milliText.text = "$milli"
            }
        }


    }

    private fun pause(){
        playFLB.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask?.cancel()
    }

    private fun RecordLapTime(){
        val labtime = this.time
        val Textview = TextView(this)

        Textview.text = "$lap LAB : ${labtime/100}.${labtime%100}"

        lablayout.addView(Textview)
        lap++

        //Down Focus
        scrollView.fullScroll(View.FOCUS_DOWN)



    }

    private  fun reset(){

        timerTask?.cancel()

        //모든 변수 초기화
        time = 0
        isRunning = false
        playFLB.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secText.text = "0"
        milliText.text = "00"
        lablayout.removeAllViews()

    }



}
