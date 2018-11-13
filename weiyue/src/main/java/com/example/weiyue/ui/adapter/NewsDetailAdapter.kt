package com.example.weiyue.ui.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.weiyue.bean.NewsDetail

class NewsDetailAdapter (data:List<NewsDetail.ItemBean>?, private val context: Context): BaseMultiItemQuickAdapter<NewsDetail.ItemBean, BaseViewHolder>(data){

    init {

    }

    override fun convert(helper: BaseViewHolder?, item: NewsDetail.ItemBean?) {

    }
}