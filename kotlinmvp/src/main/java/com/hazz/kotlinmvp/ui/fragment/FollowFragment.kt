package com.hazz.kotlinmvp.ui.fragment

import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.mvp.contranct.FollowContract
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.mvp.presenter.FollowPresenter
import com.hazz.kotlinmvp.ui.adapter.FollowAdapter

class FollowFragment : BaseFragment(), FollowContract.View {

    private var mTitle: String? = null
    private var itemList = ArrayList<HomeBean.Issue.Item>()
    private val mPresenter by lazy { FollowPresenter() }
    private val mFollowAdapter by lazy { activity?.let { FollowAdapter(it, itemList) } }


}