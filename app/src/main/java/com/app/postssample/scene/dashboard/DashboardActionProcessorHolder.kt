package com.app.postssample.scene.dashboard

import com.app.postssample.core.mvi.MviActionProcessorHolder
import com.app.postssample.core.rx.BaseSchedulerProvider
import com.app.postssample.data.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class DashboardActionProcessorHolder @Inject constructor(private val repository: PostRepository,
                                                    private val schedulerProvider: BaseSchedulerProvider
) : MviActionProcessorHolder<DashboardAction, DashboardResult> {
    override fun transformFromAction(): ObservableTransformer<DashboardAction, DashboardResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                Observable.merge(
                    shared.ofType(DashboardAction.LoadDashboardAction::class.java).compose(loadPost()),
                    shared.ofType(DashboardAction.ClickAction::class.java).compose(shareArticle())
                )
            }
        }
    }

    private fun shareArticle(): ObservableTransformer<DashboardAction.ClickAction, DashboardResult.ClickResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                Observable.just(DashboardResult.ClickResult(it.post))
            }
        }
    }

    private fun loadPost(): ObservableTransformer<DashboardAction.LoadDashboardAction, DashboardResult.LoadDashboardResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.getPosts()
                    .toObservable()
                    .map { response -> DashboardResult.LoadDashboardResult.Success(response) }
                    .cast(DashboardResult.LoadDashboardResult::class.java)
                    .onErrorReturn { t ->
                        DashboardResult.LoadDashboardResult.Failure(t.localizedMessage)
                    }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .startWith(DashboardResult.LoadDashboardResult.InFlight)
            }
        }

    }
}