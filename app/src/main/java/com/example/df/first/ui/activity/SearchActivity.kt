package com.example.df.first.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.df.first.R
import com.example.df.first.adapter.SearchAdapter
import com.example.df.first.base.BaseActivity
import com.example.df.first.base.Preference
import com.example.df.first.bean.Datas
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.SearchPresenter
import com.example.df.first.presenter.SearchPresenterImpl
import com.example.df.first.view.CollectArticleView
import com.example.df.first.view.SearchListView
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.concurrent.CompletionService

class SearchActivity : BaseActivity(), SearchListView, CollectArticleView {

    private val datas = mutableListOf<Datas>()

    private val serachPresenter: SearchPresenterImpl by lazy {
        SearchPresenterImpl(this, this)
    }

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(this, datas)
    }

    private var serachKey: String? = null
    private var searchView: SearchView? = null
    private var isSearch: Boolean = true
    private var isLike: Boolean = true
    private val isLogin:Boolean by Preference(Constant.LOGIN_KEY, false)

    override fun setLayoutId(): Int = R.layout.activity_search

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let {
            isSearch = it.getBoolean(Constant.SEARCH_KEY, true)
            isLike = it.getBoolean(Constant.LIKE_KEY, true)
        }
        toolbar.run {
            title = if (isSearch) "" else if(isLike) getString(R.string.my_like) else getString(R.string.my_bookmark)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        swipeRefreshLayout.run {
//            setOnRefreshListener(onRefreshListener)
        }
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }

        searchAdapter.run {

        }
    }
}
