package com.example.df.first.view

import com.example.df.first.bean.HomeListResponse

interface CollectArticleView {

    fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean)

    fun collectArticleFailed(errorMessage: String?, isAdd: Boolean)


}