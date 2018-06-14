package com.example.df.first.model

import com.example.df.first.presenter.HomePresenter


interface CollectOutsideArticleModel {

    /**
     * add or remove collect outside article
     * @param onCollectOutsideArticleListener HomePresenter.OnCollectOutsideArticleListener
     * @param title article title
     * @param author article author
     * @param link article link
     * @param isAdd true add, false remove
     */
    fun collectOutsideArticle(
            onCollectOutsideArticleListener: HomePresenter.OnCollectOutsideArticleListener,
            title: String, author: String, link: String, isAdd: Boolean
    )

    /**
     * cancel collect article Request
     */
    fun cancelCollectOutsideRequest()
}