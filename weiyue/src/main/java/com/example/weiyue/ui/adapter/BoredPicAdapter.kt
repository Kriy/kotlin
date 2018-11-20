package com.example.weiyue.ui.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.weiyue.R
import com.example.weiyue.bean.JdDetailBean

class BoredPicAdapter (private val context:Activity, data: List<JdDetailBean.CommentsBean>?): BaseMultiItemQuickAdapter<JdDetailBean.CommentsBean, BaseViewHolder>(data){

    init {
        addItemType(JdDetailBean.CommentsBean.TYPE_MULTIPLE, R.layout.item_jandan_pic)
        addItemType(JdDetailBean.CommentsBean.TYPE_SINGLE, R.layout.item_jandan_pic_single)
    }

    override fun convert(helper: BaseViewHolder?, item: JdDetailBean.CommentsBean?) {

    }

}