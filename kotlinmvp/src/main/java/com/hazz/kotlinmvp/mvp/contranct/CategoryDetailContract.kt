package com.hazz.kotlinmvp.mvp.contranct

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

interface CategoryDetailContract {

    interface View : IBaseView {

        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)
    }

    interface Presenter:IPresenter<View>{

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}