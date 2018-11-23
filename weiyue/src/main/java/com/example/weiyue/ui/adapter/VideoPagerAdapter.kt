package com.example.weiyue.ui.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.example.weiyue.bean.VideoChannelBean
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.video.DetailFragment

class VideoPagerAdapter(fm: FragmentManager, private val videoChannelBean: VideoChannelBean?) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): BaseFragment<*> = DetailFragment.newInstance("clientvideo_" + videoChannelBean!!.types!![position].id)

    override fun getPageTitle(position: Int): CharSequence = videoChannelBean!!.types!![position].name!!

    override fun getCount(): Int = videoChannelBean?.types?.size ?: 0

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

}