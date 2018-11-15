package com.example.weiyue.ui.news.presenter

import com.example.weiyue.bean.NewsArticleBean
import com.example.weiyue.net.BaseObserver
import com.example.weiyue.net.NewsApi
import com.example.weiyue.ui.base.BasePresenter
import com.example.weiyue.ui.news.contract.ArticleReadContract
import com.example.weiyue.utils.applySchedulers
import javax.inject.Inject

class ArticleReadPresenter @Inject
constructor(private var mNewsApi: NewsApi) : BasePresenter<ArticleReadContract.View>(), ArticleReadContract.Presenter {

    override fun getData(aid: String) {
        mNewsApi.getNewsArticle(aid)
                .applySchedulers()
                .compose(mView?.bindToLife<NewsArticleBean>())
                .subscribe(object : BaseObserver<NewsArticleBean>() {
                    override fun onSuccess(t: NewsArticleBean?) {
                        mView?.loadData(t)
                    }

                    override fun onFail(e: Throwable) {
                        mView?.loadData(null)
                    }

                })
    }

}