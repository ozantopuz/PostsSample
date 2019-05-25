package com.app.postssample.scene.detail

import com.app.postssample.core.mvi.MviActionProcessorHolder
import com.app.postssample.core.rx.SchedulerProvider
import com.app.postssample.data.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class DetailActionProcessorHolder @Inject constructor(private val repository: PostRepository,
                                                      private val schedulerProvider: SchedulerProvider
) : MviActionProcessorHolder<DetailAction, DetailResult> {
    
    override fun transformFromAction(): ObservableTransformer<DetailAction, DetailResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                Observable.merge(
                    shared.ofType(DetailAction.LoadUserAction::class.java).compose(loadUsers()),
                    shared.ofType(DetailAction.LoadCommentAction::class.java).compose(loadComments())
                )
            }
        }
    }

    private fun loadUsers(): ObservableTransformer<DetailAction.LoadUserAction, DetailResult.LoadDetailResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.getUsers()
                    .toObservable()
                    .map { response -> DetailResult.LoadDetailResult.Success(response) }
                    .cast(DetailResult.LoadDetailResult::class.java)
                    .onErrorReturn { t -> DetailResult.LoadDetailResult.Failure(t.localizedMessage) }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .startWith(DetailResult.LoadDetailResult.Loading)
            }
        }

    }

    private fun loadComments(): ObservableTransformer<DetailAction.LoadCommentAction, DetailResult.LoadCommentResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.getComments()
                    .toObservable()
                    .map { response -> DetailResult.LoadCommentResult.Success(response) }
                    .cast(DetailResult.LoadCommentResult::class.java)
                    .onErrorReturn { t -> DetailResult.LoadCommentResult.Failure(t.localizedMessage) }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .startWith(DetailResult.LoadCommentResult.Loading)
            }
        }

    }
}