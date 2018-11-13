package com.example.weiyue.ui.news.contract

import com.example.weiyue.bean.Channel
import com.example.weiyue.ui.base.BaseContract
import com.example.weiyue.ui.base.BasePresenter

interface NewsContract {

    interface View : BaseContract.BaseView {

        fun loadData(channels:List<Channel>, otherChannel: List<Channel>)

    }

    interface Presenter:BaseContract.BasePresenter{

        fun getChannel()

    }

}