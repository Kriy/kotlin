package com.example.weiyue.ui.news

import android.os.Bundle
import android.view.View
import com.example.weiyue.bean.Channel
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.ui.adapter.ChannelPagerAdapter
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.news.contract.NewsContract
import com.example.weiyue.ui.news.presenter.NewsPresenter
import com.example.weiyue.widget.SimpleMultiStateView

class NewsFragment:BaseFragment<NewsPresenter>(), NewsContract.View {

    private var mSelectedData: MutableList<Channel>? = null
    private var mUnSelectedData: MutableList<Channel>? = null
    private var selectedIndex : Int = 0
    private var selectedChannel : String? = null
    private var mChannelPagerAdapter: ChannelPagerAdapter? =  null


    override fun getContentLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSimpleMultiStateView(): SimpleMultiStateView? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initInjector(appComponent: ApplicationComponent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData(channels: List<Channel>, otherChannel: List<Channel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}