package com.example.df.first.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.df.first.R
import com.example.df.first.bean.TreeListResponse

class TypeAdapter(val cotext: Context, datas: MutableList<TreeListResponse.Data>) :
        BaseQuickAdapter<TreeListResponse.Data, BaseViewHolder>(R.layout.type_list_item, datas) {

    override fun convert(helper: BaseViewHolder, item: TreeListResponse.Data?) {
        item ?: return
        helper.setText(R.id.typeItemFirst, item.name)
        item.children?.let { children ->
            helper.setText(
                    R.id.typeItemSecond,
                    children.joinToString("      ", transform = { child ->
                        child.name
                    })
            )
        }
    }

}