package com.jokes.ui.activity

import android.content.Intent
import android.os.Bundle
import com.jokes.R
import com.jokes.common.BaseActivity
import com.jokes.common.ViewModelModule
import kotlinx.coroutines.*


class SplashActivity : BaseActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onViewReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_splash)
        activityScope.launch {
            delay(SPLASH_TIME_OUT)

            var intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun loadModules() {
        ViewModelModule.loadModules()
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }




}