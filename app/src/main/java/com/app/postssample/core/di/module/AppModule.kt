package com.app.postssample.core.di.module

import android.app.Application
import android.content.Context
import com.app.postssample.core.rx.BaseSchedulerProvider
import com.app.postssample.core.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideScheduler(): BaseSchedulerProvider = SchedulerProvider()
}