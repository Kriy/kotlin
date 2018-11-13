package com.example.weiyue.ui.news.presenter

import com.example.weiyue.MyApp
import com.example.weiyue.R
import com.example.weiyue.bean.Channel
import com.example.weiyue.database.ChannelDao
import com.example.weiyue.ui.base.BasePresenter
import com.example.weiyue.ui.news.contract.NewsContract
import org.litepal.crud.DataSupport
import javax.inject.Inject

class NewsPresenter @Inject
constructor() : BasePresenter<NewsContract.View>(), NewsContract.Presenter {

    override fun getChannel() {
        var channelList: MutableList<Channel>
        val myChannels = ArrayList<Channel>()
        val otherChannels = ArrayList<Channel>()
        channelList = ChannelDao.channels as MutableList<Channel>
        when {
            channelList.size < 1 -> {
                val channelName = MyApp.instance.resources.getStringArray(R.array.news_channel)
                val channelId = MyApp.instance.resources.getStringArray(R.array.news_channel_id)
                val channels = ArrayList<Channel>()

                for (i in channelName.indices) {
                    val channel = Channel()
                    channel.channelId = channelId[i].toString()
                    channel.channelName = channelName[i].toString()
                    channel.channelType = if (i < 1) 1 else 0
                    channel.isChannelSelect = i < channelName.size - 3
                    when {
                        i < channelId.size - 3 -> channelName.size - 3
                        else -> otherChannels.add(channel)
                    }
                }
                DataSupport.saveAllAsync(channels)
                channelList = ArrayList()
                channelList.addAll(channels)
            }
            else -> {
                channelList = ChannelDao.channels as MutableList<Channel>
                val iterator = channelList.iterator()
                while (iterator.hasNext()) {
                    val channel = iterator.next()
                    if (!channel.isChannelSelect) {
                        otherChannels.add(channel)
                        iterator.remove()
                    }
                }
                myChannels.addAll(channelList)
            }
        }
        mView?.loadData(myChannels, otherChannels)
    }

}