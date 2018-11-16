package com.example.weiyue.ui.jiandan.presenter

import android.annotation.SuppressLint
import com.example.weiyue.bean.FreshNewsBean
import com.example.weiyue.bean.JdDetailBean
import com.example.weiyue.net.BaseObserver
import com.example.weiyue.net.JanDanApi
import com.example.weiyue.ui.base.BasePresenter
import com.example.weiyue.ui.jiandan.contract.JanDanContract
import com.example.weiyue.utils.applySchedulers
import javax.inject.Inject

class JanDanPresenter @Inject
constructor(private var mJanDanApi: JanDanApi) : BasePresenter<JanDanContract.View>(), JanDanContract.Presenter {

    override fun getData(type: String, page: Int) {
        when (type) {
            JanDanApi.TYPE_FRESH -> getFreshNews(page)
            else -> getDetailData(type, page)
        }
    }

    override fun getFreshNews(page: Int) {
        mJanDanApi.getFreshNews(page)
                .applySchedulers()
                .compose(mView?.bindToLife<FreshNewsBean>())
                .subscribe(object : BaseObserver<FreshNewsBean>() {
                    override fun onSuccess(t: FreshNewsBean?) {
                        when {
                            page > 1 -> mView?.loadMoreFreshNews(t)
                            else -> mView?.loadFreshNews(t)
                        }
                    }

                    override fun onFail(e: Throwable) {
                        when {
                            page > 1 -> mView?.loadMoreFreshNews(null)
                            else -> mView?.loadFreshNews(null)
                        }
                    }
                })
    }

    @SuppressLint("CheckResult")
    override fun getDetailData(type: String, page: Int) {
        mJanDanApi.getJdDetails(type, page)
                .applySchedulers()
                .compose(mView?.bindToLife<JdDetailBean>())
                .map { it ->
                    it.comments!!
                            .asSequence()
                            .filter { it.pics != null }
                            .forEach {
                                when {
                                    it.pics!!.size > 1 -> it.viewType == JdDetailBean.CommentsBean.TYPE_MULTIPLE
                                    else -> it.viewType = JdDetailBean.CommentsBean.TYPE_SINGLE
                                }
                            }
                    it
                }
                .subscribe(object : BaseObserver<JdDetailBean>() {
                    override fun onSuccess(t: JdDetailBean?) {
                        when {
                            page > 1 -> mView?.loadMoreDetailData(type, null)
                            else -> mView?.loadDetailData(type, null)
                        }
                    }

                    override fun onFail(e: Throwable) {
                        when {
                            page > 1 -> mView?.loadMoreDetailData(type, null)
                            else -> mView?.loadDetailData(type, null)
                        }
                    }
                })
    }

}