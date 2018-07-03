package com.example.df.first.presenter

import com.example.df.first.bean.BannerResponse
import com.example.df.first.bean.HomeListResponse
import com.example.df.first.model.CollectArticleModel
import com.example.df.first.model.HomeModel
import com.example.df.first.model.HomeModelImpl
import com.example.df.first.ui.view.HomeFragmentView
import com.example.df.first.view.CollectArticleView

class HomeFragmentPresenterImpl(
        private val homeFragmentView: HomeFragmentView,
        private val collectArticleView: CollectArticleView
) : HomePresenter.OnHomeListListener, HomePresenter.OnCollectArticleListener,
        HomePresenter.OnBannerListener {

    private val homeModel: HomeModel = HomeModelImpl()
    private val collectArticleModel: CollectArticleModel = HomeModelImpl()

    override fun getHomeList(page: Int) {
        homeModel.getHomeList(this, page)
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        if (result.errorCode != 0) {
            homeFragmentView.getHomeListFailed(result.errorMsg)
            return
        }
        val total = result.data.total
        if (total == 0) {
            homeFragmentView.getHomeListZero()
            return
        }
        if (total < result.data.size) {
            homeFragmentView.getHomeListSmall(result)
            return
        }
        homeFragmentView.getHomeListSuccess(result)
    }

    override fun getHomeListFailed(errorMessage: String?) {
        homeFragmentView.getHomeListFailed(errorMessage)
    }

    override fun collectArticle(id: Int, isAdd: Boolean) {
        collectArticleModel.collectArticle(this, id, isAdd)
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectArticleView.collectArticleFailed(result.errorMsg, isAdd)
        }else{
            collectArticleView.collectArticleSuccess(result, isAdd)
        }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectArticleView.collectArticleFailed(errorMessage, isAdd)
    }

    override fun getBanner() {
        homeModel.getBanner(this)
    }

    override fun getBannerSuccess(result: BannerResponse) {
        if (result.errorCode != 0) {
            homeFragmentView.getBannerFailed(result.errorMsg)
            return
        }
        result.data ?: let {
            homeFragmentView.getBannerZero()
            return
        }
        homeFragmentView.getBannerSuccess(result)
    }

    override fun getBannerFailed(errorMessage: String?) {
        homeFragmentView.getBannerFailed(errorMessage)
    }

    fun cancelRequest(){
        homeModel.cancelBannerRequest()
        homeModel.cancelHomeListRequest()
        collectArticleModel.cancelCollectRequest()
    }


















}