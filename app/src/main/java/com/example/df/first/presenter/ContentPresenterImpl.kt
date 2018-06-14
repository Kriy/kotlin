package com.example.df.first.presenter

import com.example.df.first.bean.HomeListResponse
import com.example.df.first.model.CollectArticleModel
import com.example.df.first.model.CollectOutsideArticleModel
import com.example.df.first.model.CollectOutsideArticleModelImpl
import com.example.df.first.model.SearchModelImpl
import com.example.df.first.view.CollectArticleView

class ContentPresenterImpl(private val collectArticleView: CollectArticleView) :
        HomePresenter, HomePresenter.OnCollectArticleListener, HomePresenter.OnCollectOutsideArticleListener {

    private val collectArticleModel: CollectArticleModel = SearchModelImpl()
    private val collectOutsideArticleModel: CollectOutsideArticleModel = CollectOutsideArticleModelImpl()

    override fun collectArticle(id: Int, isAdd: Boolean) {
        collectArticleModel.collectArticle(this, id, isAdd)
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectArticleView.collectArticleFailed(result.errorMsg, isAdd)
        } else {
            collectArticleView.collectArticleSuccess(result, isAdd)
        }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectArticleView.collectArticleFailed(errorMessage, isAdd)
    }

    override fun collectOutSideArticle(title: String, author: String, link: String, isAdd: Boolean) {
        collectOutsideArticleModel.collectOutsideArticle(this, title,author, link, isAdd)
    }

    override fun collectOutsideArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectArticleView.collectArticleFailed(result.errorMsg, isAdd)
        }else{
            collectArticleView.collectArticleSuccess(result, isAdd)
        }
    }

    override fun collectOutsideArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectArticleView.collectArticleFailed(errorMessage, isAdd)
    }

    fun cancelResult(){
        collectArticleModel.cancelCollectRequest()
        collectOutsideArticleModel.cancelCollectOutsideRequest()
    }

}