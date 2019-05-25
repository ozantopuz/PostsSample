package com.app.postssample.scene.dashboard

import com.app.postssample.core.mvi.MviActionProcessorHolder
import com.app.postssample.core.rx.SchedulerProvider
import com.app.postssample.data.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class DashboardActionProcessorHolder @Inject constructor(private val repository: PostRepository,
                                                    private val schedulerProvider: SchedulerProvider
) : MviActionProcessorHolder<DashboardAction, DashboardResult> {
    override fun transformFromAction(): ObservableTransformer<DashboardAction, DashboardResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                Observable.merge(
                    shared.ofType(DashboardAction.LoadDashboardAction::class.java).compose(loadPosts()),
                    shared.ofType(DashboardAction.ClickAction::class.java).compose(clickPost())
                )
            }
        }
    }

    private fun clickPost(): ObservableTransformer<DashboardAction.ClickAction, DashboardResult.ClickResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                Observable.just(DashboardResult.ClickResult(it.post))
            }
        }
    }

    private fun loadPosts(): ObservableTransformer<DashboardAction.LoadDashboardAction, DashboardResult.LoadDashboardResult> {
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
                    .startWith(DashboardResult.LoadDashboardResult.Loading)
            }
        }

    }
}