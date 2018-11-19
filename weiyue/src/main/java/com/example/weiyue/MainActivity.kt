package com.example.weiyue

import android.os.Bundle
import android.view.View
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.ui.base.BaseActivity
import com.example.weiyue.ui.base.BaseContract
import com.example.weiyue.ui.news.NewsFragment
import com.example.weiyue.ui.video.VideoFragment
import com.example.weiyue.utils.StatusBarUtil
import com.example.weiyue.widget.BottomBar
import com.example.weiyue.widget.BottomBarTab
import com.example.weiyue.widget.SimpleMultiStateView
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : BaseActivity<BaseContract.BasePresenter>() {

    private val mFraments = arrayOfNulls<SupportFragment>(4)

    override fun getContentLayout(): Int = R.layout.activity_main

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = null

    override fun initInjector(appComponent: ApplicationComponent) {}

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        StatusBarUtil.setTranslucentForImageViewInFragment(this@MainActivity, 0, null)
        if (savedInstanceState == null) {
//            mFraments[0] = NewsFragment.newInstance()
//            mFraments[1] = VideoFragment.newInstance()
        }


        mBootomBar.addItem(BottomBarTab(this, R.drawable.ic_news, "新闻"))
                .addItem(BottomBarTab(this, R.drawable.ic_video, "视频"))
                .addItem(BottomBarTab(this, R.drawable.ic_jiandan, "煎蛋"))
                .addItem(BottomBarTab(this, R.drawable.ic_my, "我的"))

        mBootomBar.setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener {

            override fun onTabSelected(position: Int, prePosition: Int) {
                supportDelegate.showHideFragment(mFraments[position], mFraments[prePosition])
            }

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {}
        })
    }

    override fun initData() {}

    override fun onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressedSupport()
    }

    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

}
