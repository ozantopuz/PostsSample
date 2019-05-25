package com.app.postssample.scene.detail

import com.app.postssample.core.base.BaseViewModel
import com.app.postssample.core.mvi.MviActionProcessorHolder
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

class DetailViewModel(private val detailActionProcessorHolder: DetailActionProcessorHolder) :
    BaseViewModel<DetailIntent, DetailViewState, DetailAction, DetailResult>() {

    override fun initialState(): DetailViewState = DetailViewState.idle()

    override fun reducer(): BiFunction<DetailViewState, DetailResult, DetailViewState> = reducer

    override fun actionProcessorHolder(): MviActionProcessorHolder<DetailAction, DetailResult> = detailActionProcessorHolder

    override fun intentFilter(): ObservableTransformer<DetailIntent, DetailIntent> {
        return ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge<DetailIntent>(
                    shared.ofType(DetailIntent.InitialUserIntent::class.java).take(1),
                    shared.filter { it != DetailIntent.InitialUserIntent }
                )
            }
        }
    }

    init {
        connectObservableToLiveData()
    }

    override fun actionFromIntent(intent: DetailIntent): DetailAction {
        return when (intent) {
            is DetailIntent.InitialUserIntent -> DetailAction.LoadUserAction
            is DetailIntent.InitialCommentsIntent -> DetailAction.LoadCommentAction
        }
    }

    companion object {
        private val reducer = BiFunction { previousState: DetailViewState, result: DetailResult ->
            when (result) {
                is DetailResult.LoadDetailResult -> {
                    when (result) {
                        is DetailResult.LoadDetailResult.Success -> {
                            previousState.copy(isLoading = false, isError = false, errorMessage = "", users = result.userList)
                        }
                        is DetailResult.LoadDetailResult.Failure -> {
                            previousState.copy(isLoading = false, isError = true, errorMessage = result.errorMessage)
                        }
                        is DetailResult.LoadDetailResult.Loading -> {
                            previousState.copy(isLoading = true, isError = false, errorMessage = "")
                        }
                    }
                }

                is DetailResult.LoadCommentResult -> {
                    when (result) {
                        is DetailResult.LoadCommentResult.Success -> {
                            previousState.copy(isLoading = false, isError = false, errorMessage = "", comments = result.commentList)
                        }
                        is DetailResult.LoadCommentResult.Failure -> {
                            previousState.copy(isLoading = false, isError = true, errorMessage = result.errorMessage)
                        }
                        is DetailResult.LoadCommentResult.Loading -> {
                            previousState.copy(isLoading = true, isError = false, errorMessage = "")
                        }
                    }
                }
            }
        }
    }

}