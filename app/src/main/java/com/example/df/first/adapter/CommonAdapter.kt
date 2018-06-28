package com.example.df.first.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.df.first.R
import com.example.df.first.bean.FriendListResponse

class CommonAdapter(val context: Context, datas: MutableList<FriendListResponse.Data>) :
        BaseQuickAdapter<FriendListResponse.Data, BaseViewHolder>(R.layout.commont_list_item, datas) {

    override fun convert(helper: BaseViewHolder, item: FriendListResponse.Data?) {
        item ?: return
        helper.setText(R.id.commonItemTitle, item.name.trim())
    }
}