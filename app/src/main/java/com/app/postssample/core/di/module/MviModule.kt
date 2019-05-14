package com.app.postssample.core.di.module

import com.app.postssample.core.di.scope.ActivityScope
import com.app.postssample.scene.dashboard.DashboardActionProcessorHolder
import com.app.postssample.scene.dashboard.DashboardViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class MviModule {

    @Provides
    @ActivityScope
    fun provideDashboardViewModelFactory(actionProcessorHolder: DashboardActionProcessorHolder): DashboardViewModelFactory {
        return DashboardViewModelFactory(actionProcessorHolder)
    }
}