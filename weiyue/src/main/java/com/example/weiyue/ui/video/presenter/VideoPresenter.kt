package com.example.weiyue.ui.video.presenter

import com.example.weiyue.bean.VideoChannelBean
import com.example.weiyue.bean.VideoDetailBean
import com.example.weiyue.net.BaseObserver
import com.example.weiyue.net.NewsApi
import com.example.weiyue.ui.base.BasePresenter
import com.example.weiyue.ui.video.contract.VideoContract
import com.example.weiyue.utils.applySchedulers
import javax.inject.Inject

class VideoPresenter @Inject
constructor(private var mNewsApi: NewsApi) : BasePresenter<VideoContract.View>(), VideoContract.Presenter {

    /**
     * 获取视频频道列表
     *
     */
    override fun getVideoChannel() {
        mNewsApi.videoChannel
                .applySchedulers()
                .compose(mView!!.bindToLife<List<VideoChannelBean>>())
                .subscribe(object : BaseObserver<List<VideoChannelBean>>() {
                    override fun onSuccess(t: List<VideoChannelBean>?) {
                        mView?.loadVideoChannel(t)
                    }

                    override fun onFail(e: Throwable) {
                        mView?.showError()
                    }

                })

    }

    /**
     * 获取视频列表
     *
     * @param page     页码
     * @param listType 默认list
     * @param typeId   频道id
     */
    override fun getVideoDetails(page: Int, listType: String, typeId: String) {
        mNewsApi.getVideoDetail(page, listType, typeId)
                .applySchedulers()
                .compose(mView!!.bindToLife<List<VideoDetailBean>>())
                .subscribe(object : BaseObserver<List<VideoDetailBean>>() {
                    override fun onSuccess(t: List<VideoDetailBean>?) {
                        when {
                            page > 1 -> mView!!.loadMoreVideoDetails(t)
                            else -> mView!!.loadVideoDetails(t)
                        }
                    }

                    override fun onFail(e: Throwable) {
                        mView?.showError()
                    }
                })
    }
}