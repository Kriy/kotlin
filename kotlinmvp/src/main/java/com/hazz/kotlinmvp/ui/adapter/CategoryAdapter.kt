package com.hazz.kotlinmvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import com.hazz.kotlinmvp.MyApplication
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter

/**
 * Created by Terminator on 2019/2/8.
 */
class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
        CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {

    private var textTypeFace: Typeface? = null

    init {
        textTypeFace = Typeface.createFromAsset(MyApplication.context.assets,"fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    fun setData(categoryList: ArrayList<CategoryBean>){
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {

    }
}