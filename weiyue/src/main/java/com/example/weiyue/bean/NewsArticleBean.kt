package com.example.weiyue.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsArticleBean(var meta: MetaBean?, var body: BodyBean?,
                           var disclaimer: String?) {

    data class MetaBean(var id: String?, var type: String?,
                        var documentId: String?, @SerializedName("class") var classX: String?,
                        var o: Int = 0)

    data class BodyBean(var aid: String?, var staticId: String?,
                        var id: String?, var documentId: String?,
                        var title: String?, var author: String?,
                        var editorcode: String?, var source: String?,
                        var thumbnail: String?, var editTime: String?,
                        var updateTime: String?, var cTime: String?,
                        var uTime: String?, var wwwurl: String?,
                        var shareurl: String?, var commentsUrl: String?,
                        var text: String?, var commentCount: Int = 0,
                        var commentType: String?, var allowComment: String?,
                        var subscribe: SubscribeBean?, var slides: List<SlidesBean>?,
                        var praise: String?, var hasEditor: String?,
                        var hasVideo: String?, var img: List<ImgBean>?,
                        var relateDocs: List<RelateDocsBean>?) {


        override fun toString(): String {
            return "BodyBean{" +
                    "aid='" + aid + '\'' +
                    ", staticId='" + staticId + '\'' +
                    ", id='" + id + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", source='" + source + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", editTime='" + editTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", cTime='" + cTime + '\'' +
                    ", uTime='" + uTime + '\'' +
                    ", wwwurl='" + wwwurl + '\'' +
                    ", shareurl='" + shareurl + '\'' +
                    ", commentsUrl='" + commentsUrl + '\'' +
                    ", text='" + text + '\'' +
                    ", commentCount=" + commentCount +
                    ", commentType='" + commentType + '\'' +
                    ", allowComment='" + allowComment + '\'' +
                    ", subscribe=" + subscribe +
                    ", slides=" + slides +
                    ", praise='" + praise + '\'' +
                    ", hasEditor='" + hasEditor + '\'' +
                    ", hasVideo='" + hasVideo + '\'' +
                    ", img=" + img +
                    ", relateDocs=" + relateDocs +
                    '}'
        }

        data class SlidesBean(var image: String?, var title: String?,
                              var description: String?) : Serializable {

            override fun toString(): String {
                return "SlidesBean{" +
                        "image='" + image + '\'' +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        '}'
            }

        }

        data class SubscribeBean(var type: String?, var cateSource: String?,
                                 var parentid: String?, var parentname: String?,
                                 var cateid: String?, var catename: String?,
                                 var logo: String?, var description: String?,
                                 var api: String?, var show_link: Int = 0,
                                 var share_url: String?, var status: Int = 0)

        data class ImgBean(var url: String?, var size: SizeBean?) {
            data class SizeBean(var width: String?, var height: String?)
        }

        data class RelateDocsBean(var thumbnail: String?, var title: String?,
                                  var id: String?, var source: String?,
                                  var documentId: String?, var staticId: String?,
                                  var createTime: String?, var updateTime: String?,
                                  var commentsUrl: String?, var type: String?,
                                  var link: LinkBean?, var subscribe: SubscribeBeanX?,
                                  var comments: String?, var commentsall: String?,
                                  var style: StyleBean?, var phvideo: PhvideoBean?) {

            data class LinkBean(var type: String?, var url: String?)

            data class SubscribeBeanX(var cateid: String?, var type: String?,
                                      var catename: String?,
                                      var logo: String?, var description: String?,
                                      var cateSource: String?, var api: String?)

            data class StyleBean(var view: String?, var backreason: List<String>?)

            data class PhvideoBean(var channelName: String?, var length: Int = 0)
        }
    }

    override fun toString(): String {
        return "NewsArticleBean{" +
                "meta=" + meta +
                ", body=" + body +
                ", disclaimer='" + disclaimer + '\'' +
                '}'
    }
}