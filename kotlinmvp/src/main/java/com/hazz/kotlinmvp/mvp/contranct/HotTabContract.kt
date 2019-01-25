package com.hazz.kotlinmvp.mvp.contranct

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean

interface HotTabContract {

    interface View : IBaseView {

        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun getTabInfo()
    }
}