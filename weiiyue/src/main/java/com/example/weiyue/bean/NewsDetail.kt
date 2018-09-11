package com.example.weiyue.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import java.io.Serializable

class NewsDetail(var listId: String?, var type: String?,
                 var expiredTime: Int = 0, var currentPage: Int = 0,
                 var totalPage: Int = 0, var topsize: Int = 0,
                 var item: ArrayList<ItemBean>?) : Serializable {

    override fun toString(): String {
        return "NewsDetail{" +
                "listId='" + listId + '\'' +
                ", type='" + type + '\'' +
                ", expiredTime=" + expiredTime +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", topsize=" + topsize +
                ", item=" + item +
                '}'
    }

    data class ItemBean(var viewType: Int = 0, var type: String?,
                        var thumbnail: String?, var online: String?,
                        var title: String?, var showType: String?,
                        var source: String?, var subscribe: SubscribeBean?,
                        var updateTime: String?, var id: String?,
                        var documentId: String?, var staticId: String?,
                        var style: StyleBean?, var commentsUrl: String?,
                        var comments: String?, var commentsall: String?,
                        var link: LinkBean?, var simId: String?,
                        var reftype: String?, var recomToken: String?,
                        var isHasSlide: Boolean = false
    ) : Serializable, MultiItemEntity {

        override fun getItemType(): Int = viewType


        override fun toString(): String {
            return "ItemBean{" +
                    "type='" + type + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", online='" + online + '\'' +
                    ", title='" + title + '\'' +
                    ", showType='" + showType + '\'' +
                    ", source='" + source + '\'' +
                    ", subscribe=" + subscribe +
                    ", updateTime='" + updateTime + '\'' +
                    ", id='" + id + '\'' +
                    ", documentId='" + documentId + '\'' +
                    ", staticId='" + staticId + '\'' +
                    ", style=" + style +
                    ", commentsUrl='" + commentsUrl + '\'' +
                    ", comments='" + comments + '\'' +
                    ", commentsall='" + commentsall + '\'' +
                    ", link=" + link +
                    ", simId='" + simId + '\'' +
                    ", reftype='" + reftype + '\'' +
                    ", recomToken='" + recomToken + '\'' +
                    ", hasSlide=" + isHasSlide +
                    '}'
        }

        data class SubscribeBean(var cateid: String?, var type: String?,
                                 var catename: String?, var description: String?) {

            override fun toString(): String {
                return "SubscribeBean{" +
                        "cateid='" + cateid + '\'' +
                        ", type='" + type + '\'' +
                        ", catename='" + catename + '\'' +
                        ", description='" + description + '\'' +
                        '}'
            }
        }

        data class StyleBean(var type: String?, var url: String?,
                             var weburl: String?) {

            override fun toString(): String {
                return "LinkBean{" +
                        "type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        ", weburl='" + weburl + '\'' +
                        '}'
            }
        }

        data class LinkBean(var type: String?, var url: String?,
                            var weburl: String?) {

            override fun toString(): String {
                return "LinkBean{" +
                        "type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        ", weburl='" + weburl + '\'' +
                        '}'
            }
        }

        companion object {
            //广告类型
            val TYPE_ADVERT_TITLEIMG = 1

            val TYPE_ADVERT_SLIDEIMG = 2

            val TYPE_ADVERT_LONGIMG = 3
            //图片类型
            val TYPE_SLIDE = 4
            //视频类型
            val TYPE_PHVIDEO = 5

            //显示形式单图
            val TYPE_DOC_TITLEIMG = 6
            //显示形式多图
            val TYPE_DOC_SLIDEIMG = 7
        }
    }
}