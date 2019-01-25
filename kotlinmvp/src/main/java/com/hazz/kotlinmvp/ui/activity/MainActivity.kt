package com.hazz.kotlinmvp.ui.activity

import com.flyco.tablayout.listener.CustomTabEntity
import com.hazz.kotlinmvp.R
import com.hazz.kotlinmvp.base.BaseActivity

class MainActivity : BaseActivity() {

    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    override fun layoutId(): Int  = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
    }

    override fun start() {
    }

}