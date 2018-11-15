package com.example.weiyue.ui.news.contract

import com.example.weiyue.bean.NewsArticleBean
import com.example.weiyue.ui.base.BaseContract

interface ArticleReadContract {

    interface View : BaseContract.BaseView {
        fun loadData(articleBean: NewsArticleBean?)
    }

    interface Presenter : BaseContract.BasePresenter {
        fun getData(aid: String)
    }
}