package com.hazz.kotlinmvp.ui.adapter

import android.content.Context
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter

/**
 * Created by Terminator on 2019/2/8.
 */
class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
        CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {

    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {

    }
}