package com.app.postssample.scene.dashboard

import com.app.postssample.core.mvi.MviIntent
import com.app.postssample.data.entity.Post

sealed class DashboardIntent : MviIntent {

    object InitialIntent : DashboardIntent()

    data class ClickIntent(val post: Post) : DashboardIntent()
}