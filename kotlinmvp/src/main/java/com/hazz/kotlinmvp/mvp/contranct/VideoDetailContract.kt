package com.hazz.kotlinmvp.mvp.contranct

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

interface VideoDetailContract {

    interface View : IBaseView {

        fun setVideo(url: String)

        fun setVideoInfo(itemInfo: HomeBean.Issue.Item)

        fun setBackground(url: String)

        fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>)

        fun setErrorMsg(errorMsg: String)
    }

    interface Presenter : IPresenter<View> {

        fun loadVideoInfo(itemInfo: HomeBean.Issue.Item)

        fun requestRelatedVideo(id: Long)
    }

}