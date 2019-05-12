package com.app.postssample.core.mvi

import io.reactivex.ObservableTransformer

interface MviActionProcessorHolder<I: MviAction, R: MviResult>{
    fun transformFromAction(): ObservableTransformer<I, R>
}