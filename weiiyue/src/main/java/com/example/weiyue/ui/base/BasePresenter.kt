package com.example.weiyue.ui.base

abstract class BasePresenter<T> : BaseContract.BasePresenter {

    protected var mView: T? = null

    override fun attachView(view: BaseContract.BaseView) {
        this.mView = view as T
    }

    override fun detachView() {
        mView?.let {
            mView = null
        }
    }

}