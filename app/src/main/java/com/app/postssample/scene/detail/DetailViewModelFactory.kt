package com.app.postssample.scene.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

data class DetailViewModelFactory @Inject constructor(private val detailActionProcessorHolder: DetailActionProcessorHolder)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(detailActionProcessorHolder) as T
    }
}