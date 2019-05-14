package com.app.postssample.scene.dashboard

import com.app.postssample.core.mvi.MviViewState
import com.app.postssample.data.entity.Post

data class DashboardViewState(
    val isLoading: Boolean,
    val errorMessage: String,
    val isError: Boolean,
    val posts: List<Post>
) : MviViewState {

    companion object {
        fun idle(): DashboardViewState {
            return DashboardViewState(
                isLoading = false,
                isError = false,
                errorMessage = "",
                posts = emptyList()
            )
        }
    }
}