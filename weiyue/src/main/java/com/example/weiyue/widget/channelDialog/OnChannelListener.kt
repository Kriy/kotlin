package com.example.weiyue.widget.channelDialog

interface OnChannelListener {
    fun onItemMove(startPos: Int, endPos: Int)
    fun onMoveToMyChannel(startPos: Int, endPos: Int)
    fun onMoveToOtherChannel(startPos: Int, endPos: Int)
    fun onFinish(selectedChannelName: String)
}