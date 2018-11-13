package com.example.weiyue.ui.news.contract

import com.example.weiyue.bean.NewsDetail
import com.example.weiyue.ui.base.BaseContract

interface DetailContract {

    interface View : BaseContract.BaseView {

        fun loadBannerData(newsDetail: NewsDetail?)

        fun loadTopNewsData(newsDetail: NewsDetail?)

        fun loadData(itemBeanList: List<NewsDetail.ItemBean>?)

        fun loadMoreData(itemBeanList: List<NewsDetail.ItemBean>?)

    }

    interface Presenter : BaseContract.BasePresenter {

        fun getData(id: String, action: String, pullNum: Int)

    }

}