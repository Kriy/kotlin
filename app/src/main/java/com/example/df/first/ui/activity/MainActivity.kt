package com.example.df.first.ui.activity

import android.support.v7.widget.AppCompatButton
import android.widget.TextView
import com.example.df.first.R
import com.example.df.first.base.BaseActivity
import com.example.df.first.base.Preference
import com.example.df.first.constant.Constant
import com.example.df.first.ui.fragment.CommonUseFragment
import com.example.df.first.ui.fragment.HomeFragment
import com.example.df.first.ui.fragment.TypeFragment

class MainActivity : BaseActivity() {

    private var lastTime: Long = 0
    private var currentIndex = 0
    private var homeFragment: HomeFragment? = null
    private var typeFragment: TypeFragment? = null
    private var commonUseFragment: CommonUseFragment? = null
    private val fragmentManager by lazy {
        supportFragmentManager
    }

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)
    private val username: String by Preference(Constant.USERNAME_KEY, "")

    private lateinit var navigationViewUsername: TextView
    private lateinit var navigationViewLogout: AppCompatButton


    override
    fun setLayoutId(): Int = R.layout.activity_main

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun cancelRequest() {
    }


}