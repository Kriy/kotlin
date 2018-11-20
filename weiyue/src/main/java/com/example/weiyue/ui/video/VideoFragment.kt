package com.example.weiyue.ui.video

import android.os.Bundle
import android.view.View
import com.example.weiyue.R
import com.example.weiyue.bean.VideoChannelBean
import com.example.weiyue.bean.VideoDetailBean
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.component.DaggerHttpComponent
import com.example.weiyue.ui.adapter.VideoPagerAdapter
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.video.contract.VideoContract
import com.example.weiyue.ui.video.presenter.VideoPresenter
import com.example.weiyue.widget.SimpleMultiStateView
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : BaseFragment<VideoPresenter>(), VideoContract.View {
    private var mVideoPagerAdapter: VideoPagerAdapter? = null

    companion object {
        fun newInstance(): VideoFragment {
            val args = Bundle()
            val fragment = VideoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = null

    override fun initInjector(appComponent: ApplicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun getContentLayout(): Int = R.layout.fragment_video

    override fun bindView(view: View, savedInstanceState: Bundle?) {

    }

    override fun initData() {
        mPresenter?.getVideoChannel()
    }

    /**
     * 加载视频频道列表
     *
     * @param channelBean 频道列表
     */
    override fun loadVideoChannel(channelBean: List<VideoChannelBean>?) {
        mVideoPagerAdapter = VideoPagerAdapter(childFragmentManager, channelBean?.get(0))
        viewPager.adapter = mVideoPagerAdapter
        viewPager.offscreenPageLimit = 1
        viewPager.setCurrentItem(0, false)
        tabLayout.setupWithViewPager(viewPager, true)
    }

    override fun loadVideoDetails(detailBean: List<VideoDetailBean>?) {
    }

    override fun loadMoreVideoDetails(detailBean: List<VideoDetailBean>?) {
    }
}