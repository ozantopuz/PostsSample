package com.app.postssample.core.di.module

import com.app.postssample.scene.dashboard.DashboardActivity
import com.app.postssample.scene.detail.DetailActivity
import com.app.postssample.scene.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun dashboardActivity(): DashboardActivity

    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun detailActivity(): DetailActivity
}