package com.example.weiyue.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<T> {

    abstract fun onSuccess(t: T?)

    abstract fun onFail(e: Throwable)

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFail(e)
    }

    override fun onComplete() {}

}