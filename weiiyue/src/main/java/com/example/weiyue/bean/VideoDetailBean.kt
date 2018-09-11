package com.example.weiyue.bean

data class VideoDetailBean(var totalPage: Int = 0, var currentPage: String?,
                           var type: String?, var item: List<ItemBean>?) {

    data class ItemBean(var documentId: String?, var title: String?,
                        var image: String?, var thumbnail: String?,
                        var guid: String?, var type: String?,
                        var commentsall: Int = 0, var duration: Int = 0,
                        var shareUrl: String?, var commentsUrl: String?,
                        var video_url: String?, var video_size: String?,
                        var praise: String?, var tread: String?,
                        var playTime: String?)

}