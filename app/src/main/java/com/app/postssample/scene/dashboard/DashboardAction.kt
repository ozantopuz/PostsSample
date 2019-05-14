package com.app.postssample.scene.dashboard

import com.app.postssample.core.mvi.MviAction
import com.app.postssample.data.entity.Post

sealed class DashboardAction : MviAction {

    object LoadDashboardAction : DashboardAction()

    data class ClickAction(val post: Post) : DashboardAction()
}