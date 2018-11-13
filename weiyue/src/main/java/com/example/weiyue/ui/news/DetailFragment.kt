package com.example.weiyue.ui.news

import android.os.Bundle
import android.view.View
import com.example.weiyue.bean.NewsDetail
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.ui.adapter.NewsDetailAdapter
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.news.contract.DetailContract
import com.example.weiyue.ui.news.presenter.DetailPresenter
import com.example.weiyue.widget.SimpleMultiStateView

class DetailFragment:BaseFragment<DetailPresenter>(), DetailContract.View {

    private var mBannerList : MutableList<NewsDetail.ItemBean>?= mutableListOf()
    private var detailAdapter : NewsDetailAdapter? = null


    companion object {
        fun newInstance(newsId: String): DetailFragment {
            val args = Bundle()
            args.putString("newsId", newsId)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

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

    override fun loadBannerData(newsDetail: NewsDetail?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadTopNewsData(newsDetail: NewsDetail?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadData(itemBeanList: List<NewsDetail.ItemBean>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreData(itemBeanList: List<NewsDetail.ItemBean>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}