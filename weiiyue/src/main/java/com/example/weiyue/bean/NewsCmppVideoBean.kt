package com.example.weiyue.bean

class NewsCmppVideoBean(var singleVideoInfo: List<SingleVideoInfoBean>?) {

    data class SingleVideoInfoBean(var videoURLLow: String?, var videoURLMid: String?,
                                   var videoSizeLow: Int = 0, var videoSizeMid: Int = 0,
                                   var videoURLHigh: String?, var videoSizeHigh: Int = 0,
                                   var audioURL: String?, var guid: String?,
                                   var praise: String?, var tread: String?,
                                   var playTime: String?, var imgURL: String?,
                                   var smallImgURL: String?, var largeImgURL: String?,
                                   var richText: String?, var videoPublishTime: String?,
                                   var shareURL: String?, var commentsUrl: String?,
                                   var type: String?, var id: String?,
                                   var statisticID: String?, var title: String?,
                                   var videoLength: String?, var longTitle: String?,
                                   var columnName: String?, var cp: String?,
                                   var collect: String?, var lastPlayedTime: String?,
                                   var status: Int = 0, var columnId: String?,
                                   var weMedia: WeMediaBean?, var newStatus: String?) {

        data class WeMediaBean(var headPic: String?, var name: String?,
                               var desc: String?, var id: String?,
                               var type: String?)

    }

}