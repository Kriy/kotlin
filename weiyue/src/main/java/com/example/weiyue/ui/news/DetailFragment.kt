package com.example.weiyue.ui.news

import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.weiyue.MyApp
import com.example.weiyue.R
import com.example.weiyue.bean.NewsDetail
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.component.DaggerHttpComponent
import com.example.weiyue.net.NewsApi
import com.example.weiyue.net.NewsUtils
import com.example.weiyue.ui.adapter.NewsDetailAdapter
import com.example.weiyue.ui.base.BaseFragment
import com.example.weiyue.ui.jiandan.ImageBrowseActivity
import com.example.weiyue.ui.news.contract.DetailContract
import com.example.weiyue.ui.news.presenter.DetailPresenter
import com.example.weiyue.utils.ImageLoaderUtil
import com.example.weiyue.widget.ContextUtils
import com.example.weiyue.widget.CustomLoadMoreView
import com.example.weiyue.widget.NewsDelPop
import com.example.weiyue.widget.SimpleMultiStateView
import com.flyco.animation.SlideEnter.SlideRightEnter
import com.flyco.animation.SlideExit.SlideRightExit
import com.github.florent37.viewanimator.ViewAnimator
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment<DetailPresenter>(), DetailContract.View {

    private var mBannerList: MutableList<NewsDetail.ItemBean>? = mutableListOf()
    private var detailAdapter: NewsDetailAdapter? = null
    private var newsId: String? = null
    private var upPullNum = 1
    private var downPullNum = 1
    private var isRemoveHeaderView = false
    private var viewFocus: View? = null
    private var mBanner: Banner? = null
    private var newsDelPop: NewsDelPop? = null

    companion object {
        fun newInstance(newsId: String): DetailFragment {
            val args = Bundle()
            args.putString("newsId", newsId)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getContentLayout(): Int = R.layout.fragment_detail

    override fun getSimpleMultiStateView(): SimpleMultiStateView? = mSimpleMultiStateView

    override fun initInjector(appComponent: ApplicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        mPtrFrameLayout.disableWhenHorizontalMove(true)
        mPtrFrameLayout.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mPresenter?.getData(newsId!!, NewsApi.ACTION_DOWN, downPullNum)
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean = PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header)

        })

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        detailAdapter = NewsDetailAdapter(null, activity)
        mRecyclerView.adapter = detailAdapter
        detailAdapter?.setEnableLoadMore(true)
        detailAdapter?.setLoadMoreView(CustomLoadMoreView())
        detailAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        detailAdapter?.setOnLoadMoreListener({
            mPresenter?.getData(newsId!!, NewsApi.ACTION_UP, upPullNum)
        }, mRecyclerView)

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                val itemBean = baseQuickAdapter.getItem(position) as NewsDetail.ItemBean?
                toRead(itemBean)
            }
        })
        mRecyclerView.addOnItemTouchListener(object : OnItemChildClickListener() {
            override fun onSimpleItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View, position: Int) {
                val itemBean = baseQuickAdapter.getItem(position) as NewsDetail.ItemBean?
                when (view.id) {
                    R.id.iv_close -> {
                        view.height
                        val location = IntArray(2)
                        view.getLocationInWindow(location)

                        itemBean?.style?.let {
                            when {
                                //根据当前 view 的绝对坐标 判断上下箭头的可见性  具体参考 http://www.jianshu.com/p/09acbcf28635
                                ContextUtils.getScreenWidth(MyApp.instance) - 50 - location[1] < ContextUtils.dp2px(MyApp.instance, 80F)
                                -> newsDelPop
                                        ?.anchorView(view)
                                        ?.gravity(Gravity.TOP)
                                        ?.setBackReason(itemBean.style?.backreason!!, true, position)
                                        ?.show()
                                else -> newsDelPop
                                        ?.anchorView(view)
                                        ?.gravity(Gravity.BOTTOM)
                                        ?.setBackReason(itemBean.style?.backreason!!, false, position)
                                        ?.show()
                            }
                        }
                    }
                }
            }
        })

        viewFocus = View.inflate(activity, R.layout.news_detail_headerview, null)
        mBanner = viewFocus?.findViewById(R.id.banner)
        mBanner?.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                ?.setImageLoader(object : ImageLoader() {
                    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                        ImageLoaderUtil.LoadImage(activity, path, imageView)
                    }
                })
                ?.setDelayTime(3000)
                ?.setIndicatorGravity(BannerConfig.RIGHT)
                ?.setOnBannerListener { position ->
                    mBannerList?.let {
                        bannerToRead(it[position])
                    }
                }
        initDelPop()
    }

    fun initDelPop() {
        newsDelPop = NewsDelPop(activity)
                .alignCenter(false)
                .widthScale(0.95f)
                .showAnim(SlideRightEnter())
                .dismissAnim(SlideRightExit())
                .offset((-100).toFloat(), 0f)
                .dimEnabled(true)
        newsDelPop?.setOnClickListener(object : NewsDelPop.OnClickListener {
            override fun onClick(position: Int) {
                newsDelPop!!.dismiss()
                detailAdapter!!.remove(position)
                showToast(0, false)
            }
        })
    }

    override fun initData() {
        if (arguments == null) return
        newsId = arguments.getString("newsId")
        mPresenter?.getData(newsId!!, NewsApi.ACTION_DEFAULT, 1)
    }

    override fun onRetry() {
        super.onRetry()
        initData()
    }

    override fun loadBannerData(newsDetail: NewsDetail?) {
        val mTitleList = ArrayList<String>()
        val mUrlList = ArrayList<String>()
        mBannerList?.clear()

        newsDetail?.item?.forEach {
            if (!TextUtils.isEmpty(it.thumbnail)) {
                mTitleList.add(it.title!!)
                mBannerList?.add(it)
                mUrlList.add(it.thumbnail!!)
            }
        }

        if (mUrlList.size > 0) {
            mBanner?.setImages(mUrlList)
            mBanner?.setBannerTitles(mTitleList)
            mBanner?.start()
            if (detailAdapter?.headerLayoutCount!! < 1) {
                detailAdapter?.addHeaderView(viewFocus)
            }
        }
    }

    override fun loadTopNewsData(newsDetail: NewsDetail?) {
    }

    override fun loadData(itemBeanList: List<NewsDetail.ItemBean>?) {
        when (itemBeanList) {
            null -> {
                showError()
                mPtrFrameLayout.refreshComplete()
            }
            else -> {
                downPullNum++
                if (isRemoveHeaderView) {
                    detailAdapter?.removeAllHeaderView()
                }
                detailAdapter?.setNewData(itemBeanList)
                showToast(itemBeanList.size, true)
                mPtrFrameLayout.refreshComplete()
                showSuccess()
            }
        }
    }

    override fun loadMoreData(itemBeanList: List<NewsDetail.ItemBean>?) {
        when {
            itemBeanList == null -> detailAdapter?.loadMoreFail()
            itemBeanList.isEmpty() -> detailAdapter?.loadMoreFail()
            else -> {
                upPullNum++
                detailAdapter?.addData(itemBeanList)
                detailAdapter?.loadMoreComplete()
            }
        }
    }

    private fun showToast(num: Int, isRefresh: Boolean) {
        when {
            isRefresh -> tv_toast.text = String.format(resources.getString(R.string.news_toast), num.toString() + "")
            else -> tv_toast.text = "将为你减少此类内容"
        }
        rl_top_toast.visibility = View.VISIBLE
        ViewAnimator.animate(rl_top_toast)
                .newsPaper()
                .duration(1000)
                .start()
                .onStop {
                    ViewAnimator.animate(rl_top_toast)
                            .bounceOut()
                            .duration(1000)
                            .start()
                }
    }

    private fun bannerToRead(itemBean: NewsDetail.ItemBean?) {
        itemBean?.let {
            when (it.type) {
                NewsUtils.TYPE_DOC -> {
                    it.documentId?.let {
                        ArticleReadActivity.launch(activity, it)
                    }
                }
//                NewsUtils.TYPE_SLIDE -> ImageBrowseActivity.launch(activity, it)
                NewsUtils.TYPE_ADVERT -> it.link?.weburl?.let { AdvertActivity.launch(activity, it) }
                NewsUtils.TYPE_PHVIDEO -> toast("TYPE_PHVIDEO")
                else -> {
                }
            }
        }
    }

    private fun toRead(itemBean: NewsDetail.ItemBean?) {
        itemBean?.let {
            when (it.itemType) {
//                NewsDetail∂
            }
        }

    }
}