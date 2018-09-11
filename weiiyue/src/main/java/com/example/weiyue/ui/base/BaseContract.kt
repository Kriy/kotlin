package com.example.weiyue.ui.base

import com.trello.rxlifecycle2.LifecycleTransformer

interface BaseContract {

    interface BasePresenter {

        fun attachView(view: BaseView)

        fun detachView()
    }

    interface BaseView {

        fun showLoading()

        fun showSuccess()

        fun showError()

        fun showNoNet()

        fun onRetry()

        fun <T> bindToLife(): LifecycleTransformer<T>

    }

}