package com.app.postssample.scene.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

data class DashboardViewModelFactory @Inject constructor(private val dashboardActionProcessorHolder: DashboardActionProcessorHolder)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(dashboardActionProcessorHolder) as T
    }
}