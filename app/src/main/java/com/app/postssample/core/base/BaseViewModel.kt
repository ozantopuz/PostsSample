package com.app.postssample.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.postssample.core.mvi.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<I : MviIntent, S : MviViewState, A : MviAction, R : MviResult> : ViewModel(), MviViewModel<I, S> {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val intentsSubject: PublishSubject<I> = PublishSubject.create()
    private val statesLiveData: MutableLiveData<S> = MutableLiveData()
    lateinit var statesObservable: Observable<S>
    abstract fun intentFilter(): ObservableTransformer<I, I>

    override fun processIntents(intents: Observable<I>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): LiveData<S> {
        return statesLiveData
    }

    fun connectObservableToLiveData() {
        statesObservable = compose()
        disposables.add(statesObservable.subscribe {
            statesLiveData.postValue(it)
        })
    }

    abstract fun actionFromIntent(intent: I): A


    private fun compose(): Observable<S> {
        return intentsSubject
            .compose(intentFilter())
            .map(this::actionFromIntent)
            .compose(actionProcessorHolder().transformFromAction())
            .scan(initialState(), reducer())
            .distinctUntilChanged()
            .replay(1)
            .autoConnect(0)
    }

    abstract fun actionProcessorHolder(): MviActionProcessorHolder<A, R>

    abstract fun reducer(): BiFunction<S, R, S>

    abstract fun initialState(): S
}