package com.app.postssample.scene.detail

import com.app.postssample.core.mvi.MviResult
import com.app.postssample.data.entity.Comments
import com.app.postssample.data.entity.User

sealed class DetailResult : MviResult {
    sealed class LoadDetailResult : DetailResult() {

        data class Success(val userList: List<User>) : LoadDetailResult()

        data class Failure(val errorMessage: String) : LoadDetailResult()

        object Loading : LoadDetailResult()
    }

    sealed class LoadCommentResult : DetailResult() {

        data class Success(val commentList: List<Comments>) : LoadCommentResult()

        data class Failure(val errorMessage: String) : LoadCommentResult()

        object Loading : LoadCommentResult()
    }
}