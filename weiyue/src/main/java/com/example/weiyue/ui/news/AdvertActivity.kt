package com.example.weiyue.ui.news

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.weiyue.R
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.ui.base.BaseActivity
import com.example.weiyue.ui.base.BaseContract
import com.example.weiyue.widget.SimpleMultiStateView
import kotlinx.android.synthetic.main.activity_advert.*

class AdvertActivity : BaseActivity<BaseContract.BasePresenter>() {

    companion object {
        fun launch(context: Context, url: String) {
            val intent = Intent(context, AdvertActivity::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }

    override fun getContentLayout(): Int = R.layout.activity_advert

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = null

    override fun initInjector(appComponent: ApplicationComponent) {
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        setStatusBarColor(ContextCompat.getColor(this, R.color.statusBar))
        getSetting(wv_advert)
        wv_advert.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }
        }
        wv_advert.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (pb_progress == null) {
                    return
                }
                if (newProgress == 100) {
                    pb_progress.visibility = View.GONE
                } else {
                    pb_progress.visibility = View.VISIBLE
                    pb_progress.progress = newProgress
                }
            }
        }
        iv_back.setOnClickListener { finish() }
    }

    override fun initData() {
        if (intent == null) return
        val url = intent.getStringExtra("url")
        if (!TextUtils.isEmpty(url)) {
            wv_advert.loadUrl(url)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun getSetting(webView: WebView) {
        webView.settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            useWideViewPort = false
            setAppCacheEnabled(true)
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            allowFileAccessFromFileURLs = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            domStorageEnabled = true
        }
        webView.setOnLongClickListener { true }
    }


}