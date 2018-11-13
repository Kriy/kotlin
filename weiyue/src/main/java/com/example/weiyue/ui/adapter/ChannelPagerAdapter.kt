package com.example.weiyue.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.example.weiyue.bean.Channel
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.news.DetailFragment

class ChannelPagerAdapter (fm:FragmentManager, private var mChannels:List<Channel>?) : FragmentStatePagerAdapter(fm){

    fun updateChannel(channels:List<Channel>){
        this.mChannels = channels
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): BaseFragment<*> = DetailFragment.newInstance(mChannels!![position].channelId!!)

    override fun getPageTitle(position: Int): CharSequence? = mChannels!![position].channelName

    override fun getCount(): Int = mChannels?.size ?: 0

    override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE

}