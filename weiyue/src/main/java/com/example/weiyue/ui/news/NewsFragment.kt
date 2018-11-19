package com.example.weiyue.ui.news

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.View
import com.example.weiyue.R
import com.example.weiyue.bean.Channel
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.component.DaggerHttpComponent
import com.example.weiyue.event.SelectChannelEvent
import com.example.weiyue.ui.adapter.ChannelPagerAdapter
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.news.contract.NewsContract
import com.example.weiyue.ui.news.presenter.NewsPresenter
import com.example.weiyue.widget.SimpleMultiStateView
import com.example.weiyue.widget.channelDialog.ChannelDialogFragment
import kotlinx.android.synthetic.main.activity_imagebrowse.*
import kotlinx.android.synthetic.main.fragment_news_new.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class NewsFragment : BaseFragment<NewsPresenter>(), NewsContract.View {

    private var mSelectedData: MutableList<Channel>? = null
    private var mUnSelectedData: MutableList<Channel>? = null
    private var selectedIndex: Int = 0
    private var selectedChannel: String? = null
    private var mChannelPagerAdapter: ChannelPagerAdapter? = null

    companion object {
        fun newInstance(): NewsFragment {
            val args = Bundle()
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getContentLayout(): Int = R.layout.fragment_news_new

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = null

    override fun initInjector(appComponent: ApplicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                selectedIndex = position
                selectedChannel = mSelectedData?.get(position)?.channelName
            }
        })
        iv_edit.setOnClickListener {
            val dialogFragment = ChannelDialogFragment.newInstance(mSelectedData, mUnSelectedData)
            dialogFragment.show(childFragmentManager, "CHANNEL")
        }
    }

    override fun initData() {
        mPresenter?.getChannel()
    }

    override fun loadData(channels: List<Channel>, otherChannel: List<Channel>) {
        mSelectedData = channels as MutableList<Channel>
        mUnSelectedData = otherChannel as MutableList<Channel>
        mChannelPagerAdapter = ChannelPagerAdapter(childFragmentManager, channels)
        viewpager.adapter = mChannelPagerAdapter
        viewpager.offscreenPageLimit = 2
        viewpager.setCurrentItem(0, false)
        slidingTabLayout.setViewPager(viewPager)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun selectChannelEvent(selectChannelEvent: SelectChannelEvent) {
        selectChannelEvent?.let {
            val integers = mSelectedData?.map { it.channelName!! }
            integers?.let { setViewpagerPosition(it, selectChannelEvent.channelName) }
        }
    }

    private fun setViewpagerPosition(integers: List<String>?, channelName: String) {
        if (TextUtils.isEmpty(channelName) || integers == null) return
        for (j in integers.indices) {
            if (integers[j] == channelName) {
                selectedChannel = integers[j]
                selectedIndex = j
                break
            }
        }
        viewpager.postDelayed({ viewpager.setCurrentItem(selectedIndex, false) }, 100)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}