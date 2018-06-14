package com.example.df.first.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.df.first.R
import com.example.df.first.base.BaseActivity
import com.example.df.first.presenter.ContentPresenterImpl
import com.example.df.first.view.CollectArticleView
import com.just.agentweb.AgentWeb

class ContentActivity : BaseActivity(), CollectArticleView {
    private lateinit var agentWeb: AgentWeb
    private lateinit var shareTitle: String
    private lateinit var shareUrl: String
    private var shareId: Int = 0

    private val collectArticlePresenter: ContentPresenterImpl by lazy {
        ContentPresenterImpl(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
    }
}
