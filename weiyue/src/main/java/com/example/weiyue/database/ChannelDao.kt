package com.example.weiyue.database

import com.example.weiyue.bean.Channel
import org.litepal.crud.DataSupport

object ChannelDao {

    val channels: List<Channel>
        get() = DataSupport.findAll(Channel::class.java)


    fun saveChannels(channels: List<Channel>?) {
        if (channels == null) return
        if (channels.size > 0) {
            val channelList = ArrayList<Channel>()
            channelList.addAll(channels)
            DataSupport.deleteAllAsync(Channel::class.java).listen {
                DataSupport.markAsDeleted(channelList)
                DataSupport.saveAllAsync(channelList).listen {  }
            }
        }
    }

    fun  cleanChannels(){
        DataSupport.deleteAll(Channel::class.java)
    }

}