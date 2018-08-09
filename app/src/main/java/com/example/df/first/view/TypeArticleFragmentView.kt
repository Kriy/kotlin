package com.example.df.first.view

import com.example.df.first.bean.ArticleListResponse

interface TypeArticleFragmentView {

    fun getTypeArticleListSuccess(result: ArticleListResponse)

    fun getTypeArticleListFailed(errorMessage: String?)

    fun getTypeArticleListZero()

    fun getTypeArticleListSmall(result: ArticleListResponse)

}