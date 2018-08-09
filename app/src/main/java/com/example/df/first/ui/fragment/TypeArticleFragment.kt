package com.example.df.first.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.df.first.R
import com.example.df.first.adapter.TypeArticleAdapter
import com.example.df.first.base.BaseFragment
import com.example.df.first.base.Preference
import com.example.df.first.bean.ArticleListResponse
import com.example.df.first.bean.Datas
import com.example.df.first.bean.HomeListResponse
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.TypeArticlePresenterImpl
import com.example.df.first.ui.activity.LoginActivity
import com.example.df.first.view.CollectArticleView
import com.example.df.first.view.TypeArticleFragmentView
import kotlinx.android.synthetic.main.fragment_type_content.*
import toast
import top.jowanxu.wanandroidclient.ui.activity.ContentActivity

class TypeArticleFragment : BaseFragment(), TypeArticleFragmentView, CollectArticleView {

    private var mainView: View? = null
    private val datas = mutableListOf<Datas>()
    private val typeArticlePresenter: TypeArticlePresenterImpl by lazy {
        TypeArticlePresenterImpl(this, this)
    }
    private val typeArticleAdapter: TypeArticleAdapter by lazy {
        TypeArticleAdapter(activity, datas)
    }
    private var cid: Int = 0
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView ?: let {
            mainView = inflater.inflate(R.layout.fragment_type_content, container, false)
        }
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cid = arguments.getInt(Constant.CONTENT_CID_KEY)
        tabSwipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
        }
        tabRecyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = typeArticleAdapter
        }
        typeArticleAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, tabRecyclerView)
            onItemClickListener = this@TypeArticleFragment.onItemClickListener
            onItemChildClickListener = this@TypeArticleFragment.onItemChildClickListener
            setEmptyView(R.layout.fragment_home_empty)
        }
        typeArticlePresenter.getTypeArticleList(cid = cid)
    }

    override fun cancelRequest() {
        typeArticlePresenter.cancelRequest()
        typeArticleAdapter.loadMoreComplete()
        tabSwipeRefreshLayout.isRefreshing = false
    }

    override fun getTypeArticleListSuccess(result: ArticleListResponse) {
        result.data.datas?.let {
            typeArticleAdapter.run {
                val total = result.data.total
                if (result.data.offset >= total || data.size >= total) {
                    loadMoreEnd()
                    return@let
                }
                if (tabSwipeRefreshLayout.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                loadMoreComplete()
                setEnableLoadMore(true)
            }
        }
        tabSwipeRefreshLayout.isRefreshing = false
    }

    override fun getTypeArticleListFailed(errorMessage: String?) {
        typeArticleAdapter.setEnableLoadMore(false)
        typeArticleAdapter.loadMoreFail()
        errorMessage?.let {
            activity.toast(it)
        } ?: let {
            activity.toast(getString(R.string.load_failed))
        }
        tabSwipeRefreshLayout.isRefreshing = false
    }

    override fun getTypeArticleListZero() {
        activity.toast(getString(R.string.get_data_zero))
        tabSwipeRefreshLayout.isRefreshing = false
    }

    override fun getTypeArticleListSmall(result: ArticleListResponse) {
        result.data.datas?.let {
            typeArticleAdapter.run {
                replaceData(it)
                loadMoreComplete()
                loadMoreEnd()
                setEnableLoadMore(false)
            }
        }
        tabSwipeRefreshLayout.isRefreshing = false
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        activity.toast(
                if (isAdd) getString(R.string.bookmark_success) else getString(R.string.bookmark_cancel_success)
        )
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        activity.toast(
                if (isAdd) getString(R.string.bookmark_failed) else getString(R.string.bookmark_cancel_failed)
        )
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        tabSwipeRefreshLayout.isRefreshing = true
        typeArticleAdapter.setEnableLoadMore(false)
        typeArticlePresenter.getTypeArticleList(cid = cid)
    }

    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
        if (datas.size != 0) {
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, datas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, datas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, datas[position].title)
                startActivity(this)
            }
        }
    }

    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        val page = typeArticleAdapter.data.size / 20 + 1
        typeArticlePresenter.getTypeArticleList(page, cid)
    }

    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        if (datas.size != 0) {
            val data = datas[position]
            when (view.id) {
                R.id.homeItemLike -> {
                    if (isLogin) {
                        val collect = data.collect
                        data.collect = !collect
                        typeArticleAdapter.setData(position, data)
                        typeArticlePresenter.collectArticle(data.id, !collect)
                    } else {
                        Intent(activity, LoginActivity::class.java).run {
                            startActivity(this)
                        }
                        activity.toast(getString(R.string.login_please_login))
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(cid: Int): TypeArticleFragment {
            val fragment = TypeArticleFragment()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, cid)
            fragment.arguments = args
            return fragment
        }
    }

}