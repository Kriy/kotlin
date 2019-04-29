package com.hazz.kotlinmvp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.hazz.kotlinmvp.R
import com.hazz.kotlinmvp.base.BaseActivity
import com.hazz.kotlinmvp.mvp.model.bean.TabEntity
import com.hazz.kotlinmvp.showToast
import com.hazz.kotlinmvp.ui.fragment.DiscoveryFragment
import com.hazz.kotlinmvp.ui.fragment.HomeFragment
import com.hazz.kotlinmvp.ui.fragment.HotFragment
import com.hazz.kotlinmvp.ui.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min

class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mDiscovervFragment: DiscoveryFragment? = null
    private var mHotFragment: HotFragment? = null
    private var mMineFragment: MineFragment? = null

    private var mIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    override fun layoutId(): Int = R.layout.activity_main

    private fun initTab() {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    private fun switchFragment(position: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        hideFragment(beginTransaction)
        when (position) {
            0 -> {
                mHotFragment?.let {
                    beginTransaction.show(it)
                } ?: HomeFragment.getInstance(mTitles[position]).let {
                    mHomeFragment = it
                    beginTransaction.add(R.id.fl_container, it, "home")
                }
            }
            1 -> {
                mDiscovervFragment?.let {
                    beginTransaction.show(it)
                } ?: DiscoveryFragment.getInstance(mTitles[position]).let {
                    mDiscovervFragment = it
                    beginTransaction.add(R.id.fl_container, it, "discovery")
                }
            }
            2 -> {
                mHotFragment?.let {
                    beginTransaction.show(it)
                } ?: HotFragment.getInstance(mTitles[position]).let {
                    mHotFragment = it
                    beginTransaction.add(R.id.fl_container, it, "hot")
                }
            }
            3 -> {
                mMineFragment?.let {
                    beginTransaction.show(it)
                } ?: MineFragment.getInstance(mTitles[position]).let {
                    mMineFragment = it
                    beginTransaction.add(R.id.fl_container, it, "mine")
                }
            }
        }
        mIndex = position
        tab_layout.currentTab = mIndex
        beginTransaction.commitAllowingStateLoss()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscovervFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        if (tab_layout != null) {
            outState.putInt("currentTabIndex", mIndex)
        }
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun start() {
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}