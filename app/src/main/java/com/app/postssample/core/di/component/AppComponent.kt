package com.app.postssample.core.di.component

import com.app.postssample.app.App
import com.app.postssample.core.di.module.ActivityBindingModule
import com.app.postssample.core.di.module.AppModule
import com.app.postssample.core.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    ActivityBindingModule::class
])

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        abstract fun appModule(appModule: AppModule): Builder

        override fun seedInstance(instance: App?) {
            appModule(AppModule(instance!!))
        }
    }
}