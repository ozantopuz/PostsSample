package com.app.postssample.scene.detail

import com.app.postssample.core.mvi.MviAction

sealed class DetailAction : MviAction {

    object LoadUserAction : DetailAction()

    object LoadCommentAction : DetailAction()
}