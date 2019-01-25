package com.hazz.kotlinmvp.mvp.contranct

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean

interface CategoryContract {

    interface View : IBaseView {

        fun showCategory(categoryList: ArrayList<CategoryBean>)

        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        fun getCategoryData()
    }
}