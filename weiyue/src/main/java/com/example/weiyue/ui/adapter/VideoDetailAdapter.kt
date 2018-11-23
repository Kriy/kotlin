package com.example.weiyue.ui.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.weiyue.R
import com.example.weiyue.bean.VideoDetailBean
import com.example.weiyue.utils.ImageLoaderUtil
import com.example.weiyue.utils.conversionPlayTime
import com.example.weiyue.utils.conversionTime
import fm.jiecao.jcvideoplayer_lib.JCUserAction
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard

class VideoDetailAdapter(private var context: Context, @LayoutRes layoutResId: Int, data: List<VideoDetailBean.ItemBean>?)
    : BaseQuickAdapter<VideoDetailBean.ItemBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: VideoDetailBean.ItemBean) {
        val jcVideoPlayerStandard = helper.getView<JCVideoPlayerStandard>(R.id.videoplayer)
        jcVideoPlayerStandard.setUp(item.video_url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, item.title)
        JCVideoPlayer.setJcUserAction { type, _, _, _ ->
            when (type) {
                JCUserAction.ON_CLICK_START_ICON -> {
                    helper.setVisible(R.id.tv_videoduration, false)
                }
            }
        }
        ImageLoaderUtil.LoadImage(context, item.image, jcVideoPlayerStandard.thumbImageView)
        helper.setText(R.id.tv_videoduration, conversionTime(item.duration))
        item.playTime?.let {
            helper.setText(R.id.tv_playtime, conversionPlayTime(Integer.valueOf(it)!!))
        }
    }

}