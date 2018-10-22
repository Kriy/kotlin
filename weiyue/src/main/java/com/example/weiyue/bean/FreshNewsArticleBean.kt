package com.example.weiyue.bean

import android.content.Context
import java.io.Serializable

data class FreshNewsArticleBean(var status: String?, var post: PostBean?,
                                var previous_url: String?, private val titls: String?,
                                private val except: String?) : Serializable {

    data class PostBean(var id: Int = 0, var content: String?,
                        var date: String?, var modified: String?) : Serializable

    override fun toString(): String {
        return "FreshNewsArticleBean{" +
                "status='" + status + '\'' +
                ", post=" + post +
                ", previous_url='" + previous_url + '\'' +
                '}'
    }

}