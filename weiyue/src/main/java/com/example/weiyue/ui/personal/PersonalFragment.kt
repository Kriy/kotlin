package com.example.weiyue.ui.personal

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.weiyue.R
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.ui.base.BaseContract
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.base.BasePresenter
import com.example.weiyue.utils.ImageLoaderUtil
import com.example.weiyue.widget.SimpleMultiStateView
import kotlinx.android.synthetic.main.fragment_personal.*

class PersonalFragment : BaseFragment<BasePresenter<BaseContract.BaseView>>() {

    companion object {
        fun newInstance(): PersonalFragment {
            val args = Bundle()
            val fragment = PersonalFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getContentLayout(): Int = R.layout.fragment_personal

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = null

    override fun initInjector(appComponent: ApplicationComponent) {}

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        ImageLoaderUtil.LoadImage(this, "http://oon8y1sqh.bkt.clouddn.com/avatar.JPG", ivAuthor,
                RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
        val typeFace = Typeface.createFromAsset(activity.assets, "font/consolab.ttf")
        tvContacts.typeface = typeFace
        setFont(tvName, tvBlog, tvGithub, tvEmail, tvGithub, tvUrl, tvEmailUrl)

        tvUrl.setOnClickListener {
            toWeb(resources.getString(R.string.willUrl))
        }
        tvGithubUrl.setOnClickListener {
            toWeb(resources.getString(R.string.gitHubUrl))
        }
    }

    override fun initData() {}

    private fun setFont(vararg view: TextView) {
        val typeface = Typeface.createFromAsset(activity.assets, "font/consola.ttf")
        view.forEach {
            it.typeface = typeface
        }
    }

    private fun toWeb(url: String) {
        val webUrl = Uri.parse(url)
        val webIntent = Intent(Intent.ACTION_VIEW, webUrl)
        activity.startActivity(webIntent)
    }

}