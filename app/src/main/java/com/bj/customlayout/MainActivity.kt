package com.bj.customlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.bj.librarylayout.CircularArcProgressView
import com.bj.librarylayout.LoadingView
import com.bj.librarylayout.LoadingView2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener {
            postRun()
            findViewById<CircularArcProgressView>(R.id.capv_first).startAnimator(duration = 6000)
        }
    }

    private fun postRun() {
        mHandler.postDelayed(mRunnable, 16)
    }

    private val mHandler = Handler()
    private val loadingView by lazy {
        findViewById<LoadingView>(R.id.loadingView)
    }
    private val loadingView2 by lazy {
        findViewById<LoadingView2>(R.id.loadingView2)
    }
    private val btn_start by lazy {
        findViewById<Button>(R.id.btn_start)
    }

    private var percent = 1;
    private val mRunnable = Runnable {
        loadingView.updatePercent(percent)
        loadingView2.updatePercent(percent)
        percent++
        postRun()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }

}
