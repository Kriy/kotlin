package com.example.df.first.ui.activity

import android.support.v7.app.AppCompatActivity
import com.example.df.first.base.BaseActivity

class MainActivity : BaseActivity() {

    private var lastTime: Long = 0
    private var currentIndex = 0
//    private var homeFragment: HomeFragment? = null
//    private var typeFragment: TypeFragment? = null
//    private var commonUseFragment: CommonUseFragment? = null
    private val fragmentManager by lazy{
        supportFragmentManager
    }

    override
    fun setLayoutId(): Int {
        return 1
    }

    override fun cancelRequest() {
    }


}