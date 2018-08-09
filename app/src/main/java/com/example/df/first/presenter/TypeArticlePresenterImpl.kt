package com.example.df.first.presenter

import com.example.df.first.bean.ArticleListResponse
import com.example.df.first.bean.HomeListResponse
import com.example.df.first.model.CollectArticleModel
import com.example.df.first.model.HomeModelImpl
import com.example.df.first.model.TypeArticleModel
import com.example.df.first.model.TypeArticleModelImpl
import com.example.df.first.view.CollectArticleView
import com.example.df.first.view.TypeArticleFragmentView

class TypeArticlePresenterImpl(
        private val typeArticleFragmentView: TypeArticleFragmentView,
        private val collectArticleView: CollectArticleView
):TypeArticlePresenter, TypeArticlePresenter.OnTypeArticleListListener,
    HomePresenter.OnCollectArticleListener{

    private val typeArticleModel: TypeArticleModel = TypeArticleModelImpl()
    private val collectArticleModel: CollectArticleModel = HomeModelImpl()

    override fun getTypeArticleList(page: Int, cid: Int) {
        typeArticleModel.getTypeArticleList(this, page, cid)
    }

    override fun getTypeArticleListSuccess(result: ArticleListResponse) {
        if (result.errorCode != 0) {
            typeArticleFragmentView.getTypeArticleListFailed(result.errorMsg)
            return
        }
        val total = result.data.total
        if (total == 0) {
            typeArticleFragmentView.getTypeArticleListZero()
            return
        }
        if (total < result.data.size){
            typeArticleFragmentView.getTypeArticleListSmall(result)
            return
        }
        typeArticleFragmentView.getTypeArticleListSuccess(result)
    }

    override fun getTypeArticleListFailed(errorMessage: String) {
        typeArticleFragmentView.getTypeArticleListFailed(errorMessage)
    }

    override fun collectArticle(id: Int, isAdd: Boolean) {
        collectArticleModel.collectArticle(this,id,isAdd)
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

    fun cancelRequest(){
        typeArticleModel.cancelRequest()
        collectArticleModel.cancelCollectRequest()
    }

}