package com.example.weiyue.widget

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.example.weiyue.R

class CustomLoadMoreView : LoadMoreView() {

    override fun getLayoutId(): Int = R.layout.view_load_more

    override fun isLoadEndGone(): Boolean = true

    override fun getLoadingViewId(): Int = R.id.load_more_loading_view

    override fun getLoadFailViewId(): Int = R.id.load_more_load_fail_view

    override fun getLoadEndViewId(): Int = 0

}