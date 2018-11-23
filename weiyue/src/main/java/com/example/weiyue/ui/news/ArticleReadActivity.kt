package com.example.weiyue.ui.news

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.text.TextUtils
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.weiyue.R
import com.example.weiyue.bean.NewsArticleBean
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.component.DaggerHttpComponent
import com.example.weiyue.ui.base.BaseActivity
import com.example.weiyue.ui.news.contract.ArticleReadContract
import com.example.weiyue.ui.news.presenter.ArticleReadPresenter
import com.example.weiyue.utils.ImageLoaderUtil
import com.example.weiyue.utils.getTimestampString
import com.example.weiyue.utils.string2Date
import com.example.weiyue.widget.ObservableScrollView
import com.example.weiyue.widget.SimpleMultiStateView
import kotlinx.android.synthetic.main.activity_articleread.*

class ArticleReadActivity : BaseActivity<ArticleReadPresenter>(), ArticleReadContract.View {

    companion object {
        fun launch(context: Context, aid: String) {
            val intent = Intent(context, ArticleReadActivity::class.java)
            intent.putExtra("aid", aid)
            context.startActivity(intent)
        }
    }

    override fun getContentLayout(): Int = R.layout.activity_articleread

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = simpleMultiStateView

    override fun initInjector(appComponent: ApplicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        setWebViewSetting()
        setStatusBarColor(ContextCompat.getColor(this, R.color.statusBar))
        scrollView.scrollViewListener = object : ObservableScrollView.ScrollViewListener {
            override fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int) {
                when {
                    y > constraintLayout.height -> rl_top.visibility = View.VISIBLE
                    else -> rl_top.visibility = View.GONE
                }
            }
        }
        iv_back.setOnClickListener { finish() }
    }

    override fun initData() {}

    override fun loadData(articleBean: NewsArticleBean?) {
        articleBean?.let {
            tv_title.text = it.body?.title
            tv_TopUpdateTime.text = it.body?.author
            tv_updateTime.text = getTimestampString(string2Date(it.body?.updateTime!!, "yyyy/MM/dd HH:mm:ss"))

            if (it.body?.subscribe != null) {
                ImageLoaderUtil.LoadImage(this, it.body?.subscribe!!.logo, iv_logo, RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
                ImageLoaderUtil.LoadImage(this, it.body?.subscribe!!.logo, iv_topLogo, RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
                tv_topname.text = it.body?.subscribe?.cateSource
                tv_name.text = it.body?.subscribe?.cateSource
                tv_TopUpdateTime.text = it.body?.subscribe?.catename
            } else {
                tv_topname.text = it.body?.source
                tv_name.text = it.body?.source
                tv_TopUpdateTime.text = if (!TextUtils.isEmpty(articleBean.body?.author)) articleBean.body?.author else articleBean.body?.editorcode
            }

            webView.post {
                val content = articleBean.body?.text
                val url = "javascript:show_content(\'$content\')"
                webView.loadUrl(url)
                showSuccess()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebViewSetting() {
        webView.settings.apply {
            javaScriptEnabled = true
            setAppCacheEnabled(true)
            allowFileAccessFromFileURLs = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            loadsImagesAutomatically = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.loadUrl("file:///android_asset/ifeng/post_detail.html")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val stringExtra = intent.getStringExtra("aid")
                mPresenter?.getData(stringExtra)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.hasNestedScrollingParent()
            webView.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL)
        }
    }

}