package com.app.postssample.scene.dashboard

import com.app.postssample.core.base.BaseViewModel
import com.app.postssample.core.mvi.MviActionProcessorHolder
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

class DashboardViewModel(private val dashboardActionProcessorHolder: DashboardActionProcessorHolder) :
    BaseViewModel<DashboardIntent, DashboardViewState, DashboardAction, DashboardResult>() {

    override fun initialState(): DashboardViewState = DashboardViewState.idle()

    override fun reducer(): BiFunction<DashboardViewState, DashboardResult, DashboardViewState> = reducer

    override fun actionProcessorHolder(): MviActionProcessorHolder<DashboardAction, DashboardResult> = dashboardActionProcessorHolder

    override fun intentFilter(): ObservableTransformer<DashboardIntent, DashboardIntent> {
        return ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge<DashboardIntent>(
                    shared.ofType(DashboardIntent.InitialIntent::class.java).take(1),
                    shared.filter { it != DashboardIntent.InitialIntent }
                )
            }
        }
    }

    init {
        connectObservableToLiveData()
    }

    override fun actionFromIntent(intent: DashboardIntent): DashboardAction {
        return when (intent) {
            is DashboardIntent.InitialIntent -> DashboardAction.LoadDashboardAction
            is DashboardIntent.ClickIntent -> DashboardAction.ClickAction(intent.post)
        }
    }

    companion object {
        private val reducer = BiFunction { previousState: DashboardViewState, result: DashboardResult ->
            when (result) {
                is DashboardResult.LoadDashboardResult -> {
                    when (result) {
                        is DashboardResult.LoadDashboardResult.Success -> {
                            previousState.copy(isLoading = false, isError = false, errorMessage = "", posts = result.postList)
                        }
                        is DashboardResult.LoadDashboardResult.Failure -> {
                            previousState.copy(isLoading = false, isError = true, errorMessage = result.errorMessage)
                        }
                        is DashboardResult.LoadDashboardResult.InFlight -> {
                            previousState.copy(isLoading = true, isError = false, errorMessage = "")
                        }
                    }
                }

                is DashboardResult.ClickResult -> {
                    previousState.copy(isLoading = false, isError = false, errorMessage = "")
                }
            }
        }
    }

}