package com.example.df.first.presenter

import com.example.df.first.bean.ArticleListResponse

interface TypeArticlePresenter {

    fun getTypeArticleList(page: Int = 0, cid: Int)

    interface OnTypeArticleListListener {
        fun getTypeArticleListSuccess(result: ArticleListResponse)

        fun getTypeArticleListFailed(errorMessage: String)
    }
}