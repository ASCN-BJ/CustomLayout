package com.bj.customlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.bj.librarylayout.LoadingView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener {
            postRun()
        }
    }

    private fun postRun() {
        mHandler.postDelayed(mRunnable, 300)
    }

    private val mHandler = Handler()
    private val loadingView by lazy {
        findViewById<LoadingView>(R.id.loadingView)
    }
    private val btn_start by lazy {
        findViewById<Button>(R.id.btn_start)
    }

    private var percent = 1;
    private val mRunnable = Runnable {
        loadingView.updatePercent(percent)
        percent++
        postRun()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

}
