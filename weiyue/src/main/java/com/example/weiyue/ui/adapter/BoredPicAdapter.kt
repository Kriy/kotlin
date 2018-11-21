package com.example.weiyue.ui.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.weiyue.MyApp
import com.example.weiyue.R
import com.example.weiyue.bean.JdDetailBean
import com.example.weiyue.ui.jiandan.ImageBrowseActivity
import com.example.weiyue.utils.ImageLoaderUtil
import com.example.weiyue.utils.getTimestampString
import com.example.weiyue.utils.string2Date
import com.example.weiyue.widget.ContextUtils
import com.example.weiyue.widget.MultiImageView
import com.example.weiyue.widget.ShowMaxImageView

class BoredPicAdapter(private val context: Activity, data: List<JdDetailBean.CommentsBean>?) : BaseMultiItemQuickAdapter<JdDetailBean.CommentsBean, BaseViewHolder>(data) {

    init {
        addItemType(JdDetailBean.CommentsBean.TYPE_MULTIPLE, R.layout.item_jandan_pic)
        addItemType(JdDetailBean.CommentsBean.TYPE_SINGLE, R.layout.item_jandan_pic_single)
    }

    override fun convert(helper: BaseViewHolder, item: JdDetailBean.CommentsBean) {
        helper.setText(R.id.tv_author, item.comment_author)
        if (!TextUtils.isEmpty(item.comment_agent)) {
            if (item.comment_agent!!.contains("Android")) {
                helper.setText(R.id.tv_from, "来自 Android 客户端")
                        .setVisible(R.id.tv_from, true)
            } else {
                helper.setVisible(R.id.tv_from, false)
            }
        } else {
            helper.setVisible(R.id.tv_from, false)
        }

        helper.setText(R.id.tv_time, getTimestampString(string2Date(item.comment_date!!, "yyyy-MM-dd HH:mm:ss")))

        if (TextUtils.isEmpty(item.text_content)) {
            helper.setVisible(R.id.tv_content, false)
        } else {
            helper.setVisible(R.id.tv_content, true)
            val content = item.text_content!!.replace(" ", "").replace("\r", "").replace("\n", "")
            helper.setText(R.id.tv_content, content)
        }

        helper.setVisible(R.id.img_gif, item.pics!![0].contains("gif"))
                .setVisible(R.id.progress, item.pics!![0].contains("gif"))
                .setText(R.id.tv_like, item.vote_negative)
                .setText(R.id.tv_unlike, item.vote_positive)
                .setText(R.id.tv_comment_count, item.sub_comment_count)
                .addOnClickListener(R.id.img_share)

        when (helper.itemViewType) {
            JdDetailBean.CommentsBean.TYPE_MULTIPLE -> {
                val multiImageView = helper.getView<MultiImageView>(R.id.img)
                helper.setVisible(R.id.img_gif, false)
                multiImageView.setList(item.pics)
                multiImageView.setOnItemClickListener(object : MultiImageView.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        var imgUrls: List<String> = emptyList()
                        for (i in 0 until item.pics!!.size) {
                            imgUrls += item.pics!![i]
                        }
                        ImageBrowseActivity.launch(context, imgUrls, position)
                    }

                })
            }
            JdDetailBean.CommentsBean.TYPE_SINGLE -> {
                val imageView = helper.getView<ShowMaxImageView>(R.id.img)
                imageView.layoutParams.height = ContextUtils.dp2px(MyApp.instance, 250f)

                imageView.setOnClickListener {
                    var imgUrls: List<String> = emptyList()
                    imgUrls += item.pics!![0]
                    ImageBrowseActivity.launch(context, imgUrls, 0)
                }
                ImageLoaderUtil.LoadImage(context, item.pics!![0],
                        object : DrawableImageViewTarget(helper.getView<View>(R.id.img) as ImageView) {
                            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                                super.onResourceReady(resource, transition)
                                val pmWidth = ContextUtils.getScreenWidth(MyApp.instance)
                                val pmHeight = ContextUtils.getScreenHeight(MyApp.instance)
                                val sal = pmHeight.toFloat() / pmWidth
                                val actualHeight = Math.ceil((sal * resource.intrinsicWidth).toDouble()).toInt()
                                val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, actualHeight)
                                helper.getView<View>(R.id.img).layoutParams = params
                                helper.setVisible(R.id.img_gif, false)
                            }
                        })
            }
        }
    }

}