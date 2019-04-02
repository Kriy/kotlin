package com.hazz.kotlinmvp.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.transition.Transition
import com.hazz.kotlinmvp.R
import com.hazz.kotlinmvp.base.BaseActivity
import com.hazz.kotlinmvp.mvp.contranct.VideoDetailContract
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.mvp.presenter.VideoDetailPresenter
import com.hazz.kotlinmvp.ui.adapter.VideoDetailAdapter
import com.hazz.kotlinmvp.utils.StatusBarUtil
import com.scwang.smartrefresh.header.MaterialHeader
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.activity_video_detail.*
import java.text.SimpleDateFormat

class VideoDetailActivity : BaseActivity(), VideoDetailContract.View {

    companion object {
        const val IMG_TRANSITION = "IMG_TRANSITION"
        const val TRANSITION = "TRANSITION"
    }

    private val mPresenter by lazy { VideoDetailPresenter() }

    private val mAdapter by lazy { VideoDetailAdapter(this, itemList) }

    private val mFormat by lazy { SimpleDateFormat("yyyyMMddHHmmss") }

    private lateinit var itemData: HomeBean.Issue.Item

    private var orientationUtils: OrientationUtils? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private var isPlay: Boolean = false
    private var isPause: Boolean = false

    private var isTransition: Boolean = false
    private var transition: Transition? = null
    private var mMaterialHeader: MaterialHeader? = null

    override fun layoutId(): Int = R.layout.activity_video_detail

    override fun initView() {
        mPresenter.attachView(this)
        initTransition()
        initVideoViewConfig()

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemDetailClick { mPresenter.loadVideoInfo(it) }
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, mVideoView)

        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            loadVideoInfo()
        }


    }
}
