package com.hazz.kotlinmvp.mvp.contranct

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

interface HomeContract {

    interface View : IBaseView {

        fun setHomeData(homeBean: HomeBean)

        fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun requestHomeData(num: Int)

        fun loadMoreData()
    }
}