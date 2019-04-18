package com.hazz.kotlinmvp.mvp.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contranct.HomeContract
import com.hazz.kotlinmvp.mvp.model.HomeModel
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.exception.ExceptionHandle

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl: String? = null

    private val homeModel: HomeModel by lazy {
        HomeModel()
    }

    override fun requestHomeData(num: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = homeModel.requestHomeData(num)
                .flatMap({ homeBean ->
                    val bannerItemList = homeBean.issueList[0].itemList
                    bannerItemList.filter { item ->
                        item.type == "banner2" || item.type == "horizontalScrollCard"
                    }.forEach { item ->
                        bannerItemList.remove(item)
                    }
                    bannerHomeBean = homeBean
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                }).subscribe({ homeBean ->
                    mRootView?.apply {
                        nextPageUrl = homeBean.nextPageUrl
                        val newBannerItemList = homeBean.issueList[0].itemList
                        newBannerItemList.filter { item ->
                            item.type == "banner2" || item.type == "horizontalScrollCard"
                        }.forEach { item ->
                            newBannerItemList.remove(item)
                        }
                        bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)
                        setHomeData(bannerHomeBean!!)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            homeModel.loadMoreData(it)
                    .subscribe({ homeBean ->
                        mRootView?.apply {
                            val newItemList = homeBean.issueList[0].itemList
                            newItemList.filter { item ->
                                item.type == "banner2" || item.type == "horizontalScrollCard"
                            }.forEach { item ->
                                newItemList.remove(item)
                            }
                            nextPageUrl = homeBean.nextPageUrl
                            setMoreData(newItemList)
                        }
                    }, { t ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                        }
                    })
        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}