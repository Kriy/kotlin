package com.example.df.first.model

import com.example.df.first.presenter.HomePresenter

interface CollectArticleModel {

    fun collectArticle(
            onCollectArticleListener: HomePresenter.OnCollectArticleListener,
            id: Int,
            isAdd: Boolean
    )

    fun cancelCollectRequest()
}