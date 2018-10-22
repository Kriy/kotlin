package com.example.weiyue.bean

import java.io.Serializable

data class NewsImagesBean(var meta: MetaBean?, var body: BodyBean?) {

    data class MetaBean(var id: String?,
                        var type: String?,
                        var o: String?,
                        var documentId: String?)

    data class BodyBean(var newStatus: String?, var wwwurl: String?,
                        var commentsUrl: String?, var documentId: String?,
                        var staticId: String?, var showclient: String?,
                        var shareurl: String?, var author: String?,
                        var editorcode: String?, var source: String?,
                        var title: String?, var editTime: String?,
                        var updateTime: String?, var subscribe: SubscribeBean?,
                        var praise: String?, var showAdvert: Int = 0,
                        var adData: AdDataBean?, var slides: List<SlidesBean>?,
                        var recommend: List<RecommendBean>?) {

        data class SubscribeBean(var cateid: String?, var type: String?,
                                 var catename: String?, var logo: String?,
                                 var description: String?, var cateSource: String?,
                                 var backgroud: String?, var api: String?)

        data class AdDataBean(var articleAdData: ArticleAdDataBean?, var commentsAdData: CommentsAdDataBean?) {

            data class ArticleAdDataBean(var type: String?, var pid: String?,
                                         var adError: Int = 0, var errorText: String?)

            data class CommentsAdDataBean(var thumbnail: String?, var title: String?,
                                          var appSource: String?, var adId: String?,
                                          var adPositionId: String?, var pid: String?,
                                          var type: String?, var style: StyleBean?,
                                          var icon: IconBean?, var imageWidth: String?,
                                          var imageHeight: String?, var link: LinkBean?) {

                data class StyleBean(var attribute: String?, var view: String?,
                                     var backreason: List<String>?)

                data class IconBean(var showIcon: Int = 0, var text: String?)

                data class LinkBean(var type: String?, var weburl: String?,
                                    var url: String?, var pvurl: List<String>?,
                                    var async_click: List<String>?)

            }
        }

        data class SlidesBean(var image: String?, var title: String?,
                              var description: String?) : Serializable

        data class RecommendBean(var title: String?, var id: String?,
                                 var staticId: String?, var type: String?,
                                 var source: String?, var createTime: String?,
                                 var commentsUrl: String?, var links: String?,
                                 var thumbnail: String?, var commentsall: String?,
                                 var comments: String?, var reftype: String?)
    }

}