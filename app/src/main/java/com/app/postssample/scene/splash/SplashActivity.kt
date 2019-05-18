package com.app.postssample.scene.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.postssample.R
import com.app.postssample.core.util.Constants.SPLASH_SCREEN_DELAY
import com.app.postssample.core.util.Navigator.clearAndOpen
import com.app.postssample.scene.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {

    private var delayHandler : Handler? = null
    private var runnable : Runnable = Runnable { clearAndOpen<DashboardActivity>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delayHandler = Handler()
        delayHandler?.postDelayed(runnable, SPLASH_SCREEN_DELAY)
    }
}
