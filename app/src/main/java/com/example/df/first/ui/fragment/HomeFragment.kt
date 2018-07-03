package com.example.df.first.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.df.first.R
import com.example.df.first.adapter.BannerAdapter
import com.example.df.first.adapter.HomeAdapter
import com.example.df.first.base.BaseFragment
import com.example.df.first.base.Preference
import com.example.df.first.bean.BannerResponse
import com.example.df.first.bean.Datas
import com.example.df.first.bean.HomeListResponse
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.HomeFragmentPresenterImpl
import com.example.df.first.ui.view.HomeFragmentView
import com.example.df.first.ui.view.HorizontalRecyclerView
import com.example.df.first.view.CollectArticleView
import kotlinx.coroutines.experimental.Job

class HomeFragment : BaseFragment(), HomeFragmentView, CollectArticleView {

    companion object {
        private const val BANNER_TIME = 50000L
    }

    private var mainView: View? = null

    private val datas = mutableListOf<Datas>()

    private val homeFragmentPresenter: HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this, this)
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(activity, datas)
    }

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private lateinit var bannerRecyclerView: HorizontalRecyclerView

    private val bannerDatas = mutableListOf<BannerResponse.Data>()

    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter(activity, bannerDatas)
    }

    private val bannerPageSnap: PagerSnapHelper by lazy {
        PagerSnapHelper()
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private var bannerSwitchJob: Job? = null

    private var currentIndex = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        mainView ?: let {
            mainView = inflater.inflate(R.layout.fragment_home, container, false)
        }
    }


}
