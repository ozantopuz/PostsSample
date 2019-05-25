package com.app.postssample.scene.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.postssample.R
import com.app.postssample.core.util.Navigator.clearAndOpen
import com.app.postssample.scene.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        clearAndOpen<DashboardActivity>()
    }
}
