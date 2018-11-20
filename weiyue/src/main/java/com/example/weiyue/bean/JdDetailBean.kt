package com.example.weiyue.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

data class JdDetailBean(var status: String?, var current_page: Int = 0,
                        var total_comments: Int = 0, var page_count: Int = 0,
                        var count: Int = 0, var comments: List<CommentsBean>?) {

    data class CommentsBean(var viewType: Int = 0, var comment_ID: String?,
                            var comment_post_ID: String?, var comment_author: String?,
                            var comment_author_email: String?, var comment_author_url: String?,
                            var comment_author_IP: String?, var comment_date: String?,
                            var comment_date_gmt: String?, var comment_content: String?,
                            var comment_karma: String?, var comment_approved: String?,
                            var comment_agent: String?, var comment_type: String?,
                            var comment_parent: String?, var user_id: String?,
                            var comment_subscribe: String?, var comment_reply_ID: String?,
                            var vote_positive: String?, var vote_negative: String?,
                            var vote_ip_pool: String?, var sub_comment_count: String?,
                            var text_content: String?, var pics: List<String>?) : JdBaseBean(), MultiItemEntity {

        override fun getItemType(): Int {
            return viewType
        }

        companion object {
            val TYPE_SINGLE = 0
            val TYPE_MULTIPLE = 1
        }

    }

}