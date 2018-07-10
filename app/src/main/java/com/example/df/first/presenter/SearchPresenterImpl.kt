package com.example.df.first.presenter

import com.example.df.first.bean.HomeListResponse
import com.example.df.first.model.CollectArticleModel
import com.example.df.first.model.SearchModel
import com.example.df.first.view.CollectArticleView
import com.example.df.first.view.SearchListView
import top.jowanxu.wanandroidclient.model.SearchModelImpl

class SearchPresenterImpl(
        private val searchView: SearchListView,
        private val collectArticleView: CollectArticleView
) : SearchPresenter.OnSearchListListener, SearchPresenter.OnLikeListListener,
        HomePresenter.OnCollectArticleListener {

    private val searchModel: SearchModel = SearchModelImpl()
    private val collectArticleModel: CollectArticleModel = SearchModelImpl()

    override fun getSearchList(page: Int, k: String) {
        searchModel.getSearchList(this, page, k)
    }

    override fun getSearchListSuccess(result: HomeListResponse) {
        if (result.errorCode != 0) {
            searchView.getSearchListFailed(result.errorMsg)
            return
        }
        val total = result.data.total
        if (total == 0) {
            searchView.getsearchListZero()
            return
        }
        if (total < result.data.size) {
            searchView.getSearchListSmall(result)
            return
        }
        searchView.getSearchListSuccess(result)
    }

    override fun getSearchListFailed(errorMessage: String?) {
        searchView.getSearchListFailed(errorMessage)
    }

    override fun getLikeList(page: Int) {
        searchModel.getLikeList(this, page)
    }

    override fun getLikeListSuccess(result: HomeListResponse) {
        if (result.errorCode != 0) {
            searchView.getLikeListFailed(result.errorMsg)
            return
        }
        val total = result.data.total
        if (total == 0) {
            searchView.getLikeListZero()
            return
        }
        if (total < result.data.size) {
            searchView.getLikeListSmall(result)
            return
        }
        searchView.getLikeListSuccess(result)
    }

    override fun getLikeListFailed(errorMessage: String?) {
        searchView.getLikeListFailed(errorMessage)
    }

    override fun collectArticle(id: Int, isAdd: Boolean) {
        collectArticleModel.collectArticle(this, id, isAdd)
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectArticleView.collectArticleFailed(result.errorMsg, isAdd)
        } else {
            collectArticleView.collectArticleSuccess(result, isAdd)
        }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectArticleView.collectArticleFailed(errorMessage, isAdd)
    }

    fun cancelRequest(){
        searchModel.cancelRequest()
        searchModel.cancelLikeListRequest()
        collectArticleModel.cancelCollectRequest()
    }

}