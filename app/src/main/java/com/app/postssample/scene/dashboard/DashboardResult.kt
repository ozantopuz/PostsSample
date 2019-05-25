package com.app.postssample.scene.dashboard

import com.app.postssample.core.mvi.MviResult
import com.app.postssample.data.entity.Post

sealed class DashboardResult : MviResult {
    sealed class LoadDashboardResult : DashboardResult() {

        data class Success(val postList: List<Post>) : LoadDashboardResult()

        data class Failure(val errorMessage: String) : LoadDashboardResult()

        object Loading : LoadDashboardResult()
    }

    data class ClickResult(val post: Post) : DashboardResult()
}