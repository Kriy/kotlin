package com.example.weiyue.event

import com.example.weiyue.bean.Channel


class NewChannelEvent(allChannels: MutableList<Channel>?,
                      var firstChannelName:String) {

    lateinit var selectedData: MutableList<Channel>

    lateinit var unSelectedData: MutableList<Channel>

    lateinit var allChannel:List<Channel>

    init {
        allChannels?.let {
            this.allChannel = allChannel
            selectedData = ArrayList()
            unSelectedData = ArrayList()

            val iterator = it.iterator()
            while (iterator.hasNext()) {
                val channel = iterator.next()
                when{
                    channel.itemType == Channel.TYPE_MY || channel.itemType == Channel.TYPE_OTHER -> iterator.remove()
                    channel.itemType == Channel.TYPE_MY_CHANNEL -> selectedData.add(channel)
                    else -> unSelectedData.add(channel)
                }
            }
        }
    }


}