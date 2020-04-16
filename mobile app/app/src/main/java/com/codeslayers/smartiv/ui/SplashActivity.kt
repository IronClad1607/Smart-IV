package com.codeslayers.smartiv.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.codeslayers.smartiv.R

class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private var SPLASH_DELAY: Long = 3000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}
