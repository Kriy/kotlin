package com.example.df.first.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.df.first.R
import com.example.df.first.adapter.BannerAdapter
import com.example.df.first.adapter.HomeAdapter
import com.example.df.first.base.BaseFragment
import com.example.df.first.base.Preference
import com.example.df.first.bean.BannerResponse
import com.example.df.first.bean.Datas
import com.example.df.first.bean.HomeListResponse
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.HomeFragmentPresenterImpl
import com.example.df.first.ui.activity.ContentActivity
import com.example.df.first.ui.activity.LoginActivity
import com.example.df.first.ui.activity.TypeContentActivity
import com.example.df.first.ui.view.HomeFragmentView
import com.example.df.first.ui.view.HorizontalRecyclerView
import com.example.df.first.view.CollectArticleView
import inflater
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import toast

class HomeFragment : BaseFragment(), HomeFragmentView, CollectArticleView {


    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        activity.toast(
                if (isAdd) activity.getString(R.string.bookmark_success) else activity.getString(
                        R.string.bookmark_cancel_success
                )
        )
    }

    override fun getHomeListZero() {
        activity.toast(getString(R.string.get_data_zero))
        swipeRefreshLayout.isRefreshing = false
    }

    companion object {
        private const val BANNER_TIME = 50000L
    }

    private var mainView: View? = null

    private val datas = mutableListOf<Datas>()

    private val homeFragmentPresenter: HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this, this)
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(activity, datas)
    }

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private lateinit var bannerRecyclerView: HorizontalRecyclerView

    private val bannerDatas = mutableListOf<BannerResponse.Data>()

    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter(activity, bannerDatas)
    }

    private val bannerPageSnap: PagerSnapHelper by lazy {
        PagerSnapHelper()
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private var bannerSwitchJob: Job? = null

    private var currentIndex = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        mainView ?: let {
            mainView = inflater.inflate(R.layout.fragment_home, container, false)
            bannerRecyclerView = activity.inflater(R.layout.home_banner) as HorizontalRecyclerView
        }
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener { onRefreshListener }
        }
        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }
        bannerRecyclerView.run {
            layoutManager = linearLayoutManager
            bannerPageSnap.attachToRecyclerView(this)
            requestDisallowInterceptTouchEvent(true)
            setOnTouchListener(onTouchListener)
            addOnScrollListener(onScrollListener)
        }
        bannerAdapter.run {
            bindToRecyclerView(bannerRecyclerView)
            onItemChildClickListener = this@HomeFragment.onBannerItemClickListener
        }
        homeAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener, recyclerView)
            onItemClickListener = this@HomeFragment.onItemClickListener
            onItemChildClickListener = this@HomeFragment.onItemChildClickListener
            addHeaderView(bannerRecyclerView)
            setEmptyView(R.layout.fragment_home_empty)
        }
        homeFragmentPresenter.getBanner()
        homeFragmentPresenter.getHomeList()
    }

    override fun onPause() {
        super.onPause()
        startSwitchJob()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            cancelSwitchJob()
        } else {
            startSwitchJob()
        }
    }

    override fun cancelRequest() {
        homeFragmentPresenter.cancelRequest()
    }

    fun smoothScrollToPosition() = recyclerView.scrollToPosition(0)

    fun refreshData() {
        swipeRefreshLayout.isRefreshing = true
        homeAdapter.setEnableLoadMore(false)
        cancelSwitchJob()
        homeFragmentPresenter.getBanner()
        homeFragmentPresenter.getHomeList()
    }

    override fun getBannerSuccess(result: BannerResponse) {
        result.data?.let {
            bannerAdapter.replaceData(it)
            startSwitchJob()
        }
    }

    override fun getBannerFailed(errorMessage: String?) {
        errorMessage?.let {
            activity.toast(it)
        } ?: let {
            activity.toast(getString(R.string.get_data_error))
        }
    }

    override fun getBannerZero() {
        activity.toast(getString(R.string.get_data_zero))
        swipeRefreshLayout.isRefreshing = false
    }

    override fun getHomeListSmall(result: HomeListResponse) {
        result.data.datas?.let {
            homeAdapter.run {
                replaceData(it)
                loadMoreComplete()
                loadMoreEnd()
                setEnableLoadMore(false)
            }
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        result.data.datas?.let {
            homeAdapter.run {
                val total = result.data.total
                if (result.data.offset >= total || data.size >= total) {
                    loadMoreEnd()
                    return@let
                }
                if (swipeRefreshLayout.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                loadMoreEnd()
                setEnableLoadMore(true)
            }
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun getHomeListFailed(errorMessage: String?) {
        homeAdapter.setEnableLoadMore(false)
        homeAdapter.loadMoreFail()
        errorMessage?.let {
            activity.toast(it)
        } ?: let {
            activity.toast(getString(R.string.get_data_error))
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        activity.toast(
                if (isAdd) {
                    activity.getString(R.string.bookmark_failed, errorMessage)
                } else activity.getString(R.string.bookmark_cancel_failed, errorMessage)
        )
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        refreshData()
    }

    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
        if (datas.size == 0) {
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, datas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, datas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, datas[position].title)
                startActivity(this)
            }
        }
    }

    private val onBannerItemClickListener = BaseQuickAdapter.OnItemChildClickListener() { adapter, view, position ->
        if (bannerDatas.size != 0) {
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, bannerDatas[position].url)
                putExtra(Constant.CONTENT_TITLE_KEY, bannerDatas[position].title)
                startActivity(this)
            }
        }
    }

    private val onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        if (datas.size != 0) {
            val data = datas[position]
            when (view.id) {
                R.id.homeItemType -> {
                    data.chapterName ?: let {
                        activity.toast(getString(R.string.type_null))
                        return@OnItemChildClickListener
                    }
                    Intent(activity, TypeContentActivity::class.java).run {
                        putExtra(Constant.CONTENT_TARGET_KEY, true)
                        putExtra(Constant.CONTENT_TITLE_KEY, data.chapterName)
                        putExtra(Constant.CONTENT_CID_KEY, data.chapterId)
                        startActivity(this)
                    }
                }
                R.id.homeItemLike -> {
                    if (isLogin) {
                        val collect = data.collect
                        data.collect = !collect
                        homeAdapter.setData(position, data)
                        homeFragmentPresenter.collectArticle(data.id, !collect)
                    } else {
                        Intent(activity, LoginActivity::class.java).run {
                            startActivityForResult(this, Constant.MAIN_REQUEST_CODE)
                        }
                        activity.toast(getString(R.string.login_please_login))
                    }
                }
            }
        }
    }

    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        val page = homeAdapter.data.size / 20 + 1
        homeFragmentPresenter.getHomeList(page)
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    currentIndex = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                    startSwitchJob()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cancelSwitchJob()
    }

    private val onTouchListener = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                cancelSwitchJob()
            }
        }
        false
    }

    private fun getBannerSwitchJob() = launch {
        repeat(Int.MAX_VALUE) {
            if (bannerDatas.size == 0) {
                return@launch
            }
            delay(BANNER_TIME)
            currentIndex++
            val index = currentIndex % bannerDatas.size
            bannerRecyclerView.smoothScrollToPosition(index)
            currentIndex = index
        }
    }

    private fun startSwitchJob() = bannerSwitchJob?.run {
        if (!isActive) {
            bannerSwitchJob = getBannerSwitchJob().apply { start() }
        }
    } ?: let {
        bannerSwitchJob = getBannerSwitchJob().apply { start() }
    }

    private fun cancelSwitchJob() = bannerSwitchJob?.run {
        if (isActive) {
            cancel()
        }
    }


}
