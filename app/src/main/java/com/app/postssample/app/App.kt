package com.app.postssample.app

import com.app.postssample.core.di.component.DaggerAppComponent
import com.app.postssample.core.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}





