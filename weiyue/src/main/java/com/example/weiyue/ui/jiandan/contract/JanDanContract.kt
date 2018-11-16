package com.example.weiyue.ui.jiandan.contract

import com.example.weiyue.bean.FreshNewsBean
import com.example.weiyue.bean.JdDetailBean
import com.example.weiyue.ui.base.BaseContract

interface JanDanContract {

    interface View : BaseContract.BaseView {

        fun loadFreshNews(freshNewsBean: FreshNewsBean?)

        fun loadMoreFreshNews(freshNewsBean: FreshNewsBean?)

        fun loadDetailData(type: String, jdDetailBean: JdDetailBean?)

        fun loadMoreDetailData(type: String, jdDetailBean: JdDetailBean?)

    }

    interface Presenter : BaseContract.BasePresenter {

        fun getData(type: String, page: Int)

        fun getFreshNews(page: Int)

        fun getDetailData(type: String, page: Int)

    }

}