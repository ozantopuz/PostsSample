package com.app.postssample.core.di.module

import com.app.postssample.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = [(MviModule::class)])
    abstract fun mainActivity(): MainActivity
}