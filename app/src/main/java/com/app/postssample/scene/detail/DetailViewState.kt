package com.app.postssample.scene.detail

import com.app.postssample.core.mvi.MviViewState
import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.User

data class DetailViewState(
    val isLoading: Boolean,
    val errorMessage: String,
    val isError: Boolean,
    val users: List<User>,
    val comments: List<Comments>
) : MviViewState {

    companion object {
        fun idle(): DetailViewState {
            return DetailViewState(
                isLoading = false,
                isError = false,
                errorMessage = "",
                users = emptyList(),
                comments = emptyList()
            )
        }
    }
}