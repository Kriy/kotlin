package com.hazz.kotlinmvp.mvp.present

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contranct.HotTabContract
import com.hazz.kotlinmvp.mvp.model.HotTabModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle

class HotTabPresenter : BasePresenter<HotTabContract.View>(), HotTabContract.Presenter {

    private val hotTabModel by lazy { HotTabModel() }

    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = hotTabModel.getTabInfo()
                .subscribe({ tabInfo ->
                    mRootView?.setTabInfo(tabInfo)
                }, { throwable ->
                    mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                })
        addSubscription(disposable)
    }

}