package com.example.weiyue.bean

data class VideoChannelBean(var totalPage: Int = 0, var currentPage: String?,
                            var type: String?, var types: List<TypesBean>?,
                            var item: List<ItemBean>?) {

    data class TypesBean(var id: Int = 0, var name: String?,
                         var chType: String?, var position: String?)

    data class ItemBean(var documentId: String?, var title: String?,
                        var image: String, var thumbnail: String?,
                        var guid: String?, var type: String?,
                        var duration: Int = 0, var shareUrl: String?,
                        var commentsUrl: String?, var video_url: String?,
                        var video_size: Int = 0, var praise: String?,
                        var tread: String?, var playTime: String?,
                        var commentsall: Int = 0)
}