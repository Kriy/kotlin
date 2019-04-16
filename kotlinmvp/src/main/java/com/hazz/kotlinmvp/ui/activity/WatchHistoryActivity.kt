package com.hazz.kotlinmvp.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.hazz.kotlinmvp.Constants
import com.hazz.kotlinmvp.MyApplication
import com.hazz.kotlinmvp.R
import com.hazz.kotlinmvp.base.BaseActivity
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.ui.adapter.WatchHistoryAdapter
import com.hazz.kotlinmvp.utils.StatusBarUtil
import com.hazz.kotlinmvp.utils.WatchHistoryUtils
import kotlinx.android.synthetic.main.layout_watch_history.*
import java.util.*

class WatchHistoryActivity : BaseActivity() {

    private var itemListData = ArrayList<HomeBean.Issue.Item>()

    companion object {
        private const val HISTORY_MAX = 20
    }

    override fun layoutId(): Int = R.layout.layout_watch_history

    override fun initData() {
        multipleStatusView.showLoading()
        itemListData = queryWatchHistory()
    }

    override fun initView() {
        toolbar.setNavigationOnClickListener { finish() }
        val mAdapter = WatchHistoryAdapter(this, itemListData, R.layout.item_video_small_card)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
        if (itemListData.size > 0) {
            multipleStatusView.showContent()
        } else {
            multipleStatusView.showEmpty()
        }
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
        StatusBarUtil.setPaddingSmart(this, mRecyclerView)
    }

    override fun start() {
    }

    private fun queryWatchHistory(): ArrayList<HomeBean.Issue.Item> {
        val watchList = ArrayList<HomeBean.Issue.Item>()
        val hisAll = WatchHistoryUtils.getAll(Constants.FILE_WATCH_HISTORY_NAME, MyApplication.context) as Map<*, *>
        val keys = hisAll.keys.toTypedArray()
        Arrays.sort(keys)
        val keyLength = keys.size
        val hisLength = if (keyLength > HISTORY_MAX) HISTORY_MAX else keyLength
        (1..hisLength).mapTo(watchList) {
            WatchHistoryUtils.getObject(Constants.FILE_COLLECTION_NAME, MyApplication.context,
                    keys[keyLength - it] as String) as HomeBean.Issue.Item
        }
        return watchList
    }
}