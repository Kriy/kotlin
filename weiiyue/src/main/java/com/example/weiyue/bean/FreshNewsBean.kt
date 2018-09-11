package com.example.weiyue.bean

import java.io.Serializable

data class FreshNewsBean(var status: String?, var count: Int = 0,
                         var count_total: Int = 0, var pages: Int = 0,
                         var posts: MutableList<PostsBean>?) : Serializable {

    data class PostsBean(var id: Int = 0, var url: String?,
                         var title: String?, var excerpt: String?,
                         var date: String?, var author: AuthorBean?,
                         var comment_count: Int = 0, var comment_status: String?,
                         var custom_fields: CustomFieldsBean?, var tags: List<TagsBean>?) : JdBaseBean(), Serializable {

        data class AuthorBean(var id: Int = 0, var slug: String?,
                              var name: String?, var first_name: String?,
                              var last_name: String?, var nickname: String?,
                              var url: String?, var description: String?) : Serializable

        data class CustomFieldsBean(var thumb_c: List<String>?) : Serializable

        data class TagsBean(var id: Int = 0, var slug: String?,
                            var title: String?, var description: String,
                            var post_count: Int = 0) : Serializable

    }

}