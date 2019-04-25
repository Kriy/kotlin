package com.hazz.kotlinmvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hazz.kotlinmvp.R
import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.showToast
import com.hazz.kotlinmvp.ui.activity.AboutActivity
import com.hazz.kotlinmvp.ui.activity.ProfileHomePageActivity
import com.hazz.kotlinmvp.ui.activity.WatchHistoryActivity
import com.hazz.kotlinmvp.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment(), View.OnClickListener {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        activity?.let {
            StatusBarUtil.darkMode(it)
            StatusBarUtil.setPaddingSmart(it, toolbar)

            tv_view_homepage.setOnClickListener { this }
            iv_avatar.setOnClickListener(this)
            iv_about.setOnClickListener(this)

            tv_collection.setOnClickListener(this)
            tv_comment.setOnClickListener(this)

            tv_mine_message.setOnClickListener(this)
            tv_mine_attention.setOnClickListener(this)
            tv_mine_cache.setOnClickListener(this)
            tv_watch_history.setOnClickListener(this)
            tv_feedback.setOnClickListener(this)
        }
    }

    override fun lazyLoad() {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_avatar, R.id.tv_view_homepage -> {
                val intent = Intent(activity, ProfileHomePageActivity::class.java)
                startActivity(intent)
            }
            R.id.iv_about -> {
                val intent = Intent(activity, AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_collection -> showToast("收藏")
            R.id.tv_comment -> showToast("评论")
            R.id.tv_mine_message -> showToast("我的消息")
            R.id.tv_mine_attention -> showToast("我的关注")
            R.id.tv_watch_history -> startActivity(Intent(activity, WatchHistoryActivity::class.java))
            R.id.tv_feedback -> showToast("意见反馈")
        }
    }
}