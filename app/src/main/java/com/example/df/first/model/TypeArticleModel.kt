package com.example.df.first.model

import com.example.df.first.presenter.TypeArticlePresenter

interface TypeArticleModel {

    fun getTypeArticleList(
            onTypeArticleListListener: TypeArticlePresenter.OnTypeArticleListListener,
            page: Int = 0,
            cid: Int
    )

    fun cancelRequest()
}