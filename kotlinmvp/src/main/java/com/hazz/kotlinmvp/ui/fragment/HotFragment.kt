package com.hazz.kotlinmvp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.mvp.contranct.HotTabContract
import com.hazz.kotlinmvp.mvp.presenter.HotTabPresenter

/**
 * Created by Terminator on 2019/4/17.
 */
class HotFragment : BaseFragment(), HotTabContract.View {

    private val mPresenter by lazy { HotTabPresenter() }

    private var mTitle: String? = null

    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot


}