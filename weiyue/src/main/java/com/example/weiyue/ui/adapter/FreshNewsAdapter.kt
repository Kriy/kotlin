package com.example.weiyue.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.weiyue.R
import com.example.weiyue.bean.FreshNewsBean
import com.example.weiyue.utils.ImageLoaderUtil

class FreshNewsAdapter(private val context: Context, data: List<FreshNewsBean.PostsBean>?) : BaseQuickAdapter<FreshNewsBean.PostsBean, BaseViewHolder>(R.layout.item_freshnews, data), BaseQuickAdapter.OnItemClickListener {

    override fun convert(helper: BaseViewHolder, item: FreshNewsBean.PostsBean) {
        helper.setText(R.id.tv_title, item.title)
                .setText(R.id.tv_info, item.author!!.name)
                .setText(R.id.tv_commentsize, item.comment_count.toString())
        ImageLoaderUtil.LoadImage(context, item.custom_fields!!.thumb_c!![0], helper.getView<ImageView>(R.id.iv_logo))
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }
}