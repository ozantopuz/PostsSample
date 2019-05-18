package com.app.postssample.scene.detail

import com.app.postssample.core.mvi.MviIntent

sealed class DetailIntent : MviIntent {

    object InitialUserIntent : DetailIntent()

    object InitialCommentsIntent : DetailIntent()
}