package com.app.postssample.core.mvi

import io.reactivex.Observable

interface MviView<I : MviIntent, in S : MviViewState> {

    fun intents(): Observable<I>

    fun render(state: S)
}