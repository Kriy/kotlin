package com.hazz.kotlinmvp.base

interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()
}