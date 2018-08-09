package com.example.df.first.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.df.first.R
import com.example.df.first.bean.Datas

class TypeArticleAdapter(val context: Context, datas: MutableList<Datas>) :
        BaseQuickAdapter<Datas, BaseViewHolder>(R.layout.home_list_item, datas) {

    override fun convert(helper: BaseViewHolder, item: Datas?) {
        item ?: return
        helper.setText(R.id.homeItemTitle, item.title)
                .setText(R.id.homeItemAuthor, item.author)
                .setVisible(R.id.homeItemType, false)
                .setText(R.id.homeItemDate, item.niceData)
                .setImageResource(R.id.homeItemLike,
                        if (item.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like)
                .addOnClickListener(R.id.homeItemLike)
    }
}