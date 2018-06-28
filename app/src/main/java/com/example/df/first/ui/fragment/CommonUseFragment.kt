package com.example.df.first.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.df.first.R
import com.example.df.first.adapter.CommonAdapter
import com.example.df.first.adapter.CommonUseTagAdapter
import com.example.df.first.adapter.HotTagAdapter
import com.example.df.first.base.BaseFragment
import com.example.df.first.bean.FriendListResponse
import com.example.df.first.bean.HotKeyResponse
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.CommonUseFragmentPresenterImpl
import com.example.df.first.ui.activity.SearchActivity
import com.example.df.first.view.CommonUseFragmentView
import com.zhy.view.flowlayout.TagFlowLayout
import inflater
import kotlinx.android.synthetic.main.fragment_common.*
import toast
import top.jowanxu.wanandroidclient.ui.activity.ContentActivity

class CommonUseFragment : BaseFragment(), CommonUseFragmentView {

    private var mainView: View? = null
    private val datas = mutableListOf<FriendListResponse.Data>()
    private val commonUserFragmentPresenter: CommonUseFragmentPresenterImpl by lazy {
        CommonUseFragmentPresenterImpl(this)
    }

    private val commonAdapter: CommonAdapter by lazy {
        CommonAdapter(activity, datas)
    }

    private lateinit var flowLayout: LinearLayout

    private lateinit var hotTagFlowLayout: TagFlowLayout

    private val hotTagDatas = mutableListOf<HotKeyResponse.Data>()

    private val hotTagAdapter: HotTagAdapter by lazy {
        HotTagAdapter(activity, hotTagDatas)
    }

    private lateinit var commonUseTagFlowLayout: TagFlowLayout

    private val commonUseDatas = mutableListOf<FriendListResponse.Data>()

    private val commontUseTagAdapter: CommonUseTagAdapter by lazy {
        CommonUseTagAdapter(activity, commonUseDatas)
    }

    private lateinit var bookmarkTitle: TextView

    private lateinit var bookmarkTagFlowLayout: TagFlowLayout

    private val bookmarkUserDatas = mutableListOf<FriendListResponse.Data>()

    private val bookmarkTagAdapter: CommonUseTagAdapter by lazy {
        CommonUseTagAdapter(activity, bookmarkUserDatas)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView?.let {
            mainView = inflater.inflate(R.layout.fragment_common, container, false) as LinearLayout
            flowLayout = activity.inflater(R.layout.common_hot) as LinearLayout
            hotTagFlowLayout = flowLayout.findViewById(R.id.hotFlowlayout)
            commonUseTagFlowLayout = flowLayout.findViewById(R.id.commonUseFlowLayout)
            bookmarkTitle = flowLayout.findViewById(R.id.bookmarkTitle)
            bookmarkTagFlowLayout = flowLayout.findViewById(R.id.bookmarkFlowLayout)
        }
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookmarkTagFlowLayout.run {
            adapter = bookmarkTagAdapter
            setOnTagClickListener(onBookmarkTagClickListener)
        }
        hotTagFlowLayout.run {
            adapter = hotTagAdapter
            setOnTagClickListener(onHotTagClickListener)
        }
        commonUseTagFlowLayout.run {
            adapter = commontUseTagAdapter
            setOnTagClickListener(onCommonUseTagClickListener)
        }
        commonSwipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener { onRefreshListener }
        }
        commonRecyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = commonAdapter
        }
        commonAdapter.run {
            bindToRecyclerView(commonRecyclerView)
            addHeaderView(flowLayout)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden && isFirst) {
            commonUserFragmentPresenter.getFriendList()
            isFirst = false
        }
    }

    override fun cancelRequest() {
        commonUserFragmentPresenter.cancelRequest()
    }

    override fun getFriendListSuccess(
            bookmarkResult: FriendListResponse?,
            commonResult: FriendListResponse,
            hotResult: HotKeyResponse
    ) {
        bookmarkResult?.let {
            it.data?.let {
                bookmarkTitle.visibility = View.VISIBLE
                bookmarkTagFlowLayout.visibility = View.VISIBLE
                bookmarkUserDatas.clear()
                bookmarkUserDatas.addAll(it)
                bookmarkTagAdapter.notifyDataChanged()
            } ?: let {
                bookmarkTitle.visibility = View.GONE
                bookmarkTagFlowLayout.visibility = View.GONE
            }
        }
        commonResult.data?.let {
            commonUseDatas.clear()
            commonUseDatas.addAll(it)
            commontUseTagAdapter.notifyDataChanged()
        }
        hotResult.data?.let {
            hotTagDatas.clear()
            hotTagDatas.addAll(it)
            hotTagAdapter.notifyDataChanged()
        }
        commonSwipeRefreshLayout.isRefreshing = false
    }

    override fun getFriendListFailed(errorMessage: String?) {
        errorMessage?.let {
            activity.toast(it)
        }?.let {
            activity.toast("Failed to get the data")
        }
        commonSwipeRefreshLayout.isRefreshing = false
    }

    override fun getFriendListZero() {
        activity.toast("Get data size is zero")
        commonSwipeRefreshLayout.isRefreshing = false
    }

    fun refreshData(){
        commonSwipeRefreshLayout.isRefreshing = true
        commonUserFragmentPresenter.getFriendList()
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        refreshData()
    }

    private val onBookmarkTagClickListener = TagFlowLayout.OnTagClickListener { view, position, parent ->
        if (bookmarkUserDatas.size != 0) {
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, bookmarkUserDatas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, bookmarkUserDatas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, bookmarkUserDatas[position].name)
                startActivity(this)
            }
        }
        true
    }

    private val onCommonUseTagClickListener = TagFlowLayout.OnTagClickListener { view, position, parent ->
        if (commonUseDatas.size != 0) {
            Intent(activity, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, commonUseDatas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, commonUseDatas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, commonUseDatas[position].name)
            }
        }
        true
    }

    private val onHotTagClickListener = TagFlowLayout.OnTagClickListener { _, position, _ ->
        if (hotTagDatas.size != 0) {
            Intent(activity, SearchActivity::class.java).run {
                putExtra(Constant.SEARCH_KEY, true)
                putExtra(Constant.CONTENT_TITLE_KEY, hotTagDatas[position].name)
                startActivityForResult(this, Constant.MAIN_LIKE_REQUEST_CODE)
            }
        }
        true
    }
}