package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contranct.SearchContrack
import com.hazz.kotlinmvp.mvp.model.SearchModel
import com.hazz.kotlinmvp.net.exception.ExceptionHandle

class SearchPresenter : BasePresenter<SearchContrack.View>(), SearchContrack.Presenter {

    private var nextPageUrl: String? = null

    private val searchModel by lazy { SearchModel() }

    override fun requestHotWordData() {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.requestHotWordData()
                .subscribe({ string ->
                    mRootView?.apply {
                        setHotWordData(string)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun querySearchData(words: String) {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.getSearchResult(words)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        if (issue.count > 0 && issue.itemList.size > 0) {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        } else {
                            setEmptyView()
                        }
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun loadMoreData() {
        checkViewAttached()
        nextPageUrl?.let {
            addSubscription(disposable = searchModel.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setSearchResult(issue)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                        }
                    }))
        }
    }
}
