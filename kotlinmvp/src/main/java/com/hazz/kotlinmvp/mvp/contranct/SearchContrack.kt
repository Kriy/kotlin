package com.hazz.kotlinmvp.mvp.contranct

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

interface SearchContrack {

    interface View : IBaseView {

        fun setHotWordData(string: ArrayList<String>)

        fun setSearchResult(issue: HomeBean.Issue)

        fun closeSoftKeyboard()

        fun setEmptyView()

        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter:IPresenter<View>{

        fun requestHotWordData()

        fun querySearchData()

        fun loadMoreData()
    }

}