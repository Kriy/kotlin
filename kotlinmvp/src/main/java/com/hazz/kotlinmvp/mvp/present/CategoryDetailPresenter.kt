package com.hazz.kotlinmvp.mvp.present

import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.mvp.contranct.CategoryDetailContract
import com.hazz.kotlinmvp.mvp.model.CategoryDetailModel

/**
 * Created by Terminator on 2019/2/21.
 */
class CategoryDetailPresenter : BasePresenter<CategoryDetailContract.View>(), CategoryDetailContract.Presenter {

    private val categoryDetailModel by lazy {
        CategoryDetailModel()
    }

    override fun loadMoreData() {
    }

    override fun getCategoryDetailList(id: Long) {
    }
}