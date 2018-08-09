package com.example.df.first.model

import cancelByActive
import com.example.df.first.bean.ArticleListResponse
import com.example.df.first.constant.Constant
import com.example.df.first.presenter.TypeArticlePresenter
import com.example.df.first.retrofit.RetrofitHelper
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import tryCatch

class TypeArticleModelImpl : TypeArticleModel {

    private var typeArticleListAsync: Deferred<ArticleListResponse>? = null

    override fun getTypeArticleList(onTypeArticleListListener: TypeArticlePresenter.OnTypeArticleListListener, page: Int, cid: Int) {
        async(UI) {
            tryCatch({
                it.printStackTrace()
                onTypeArticleListListener.getTypeArticleListFailed(it.toString())
            }) {
                typeArticleListAsync?.cancelByActive()
                typeArticleListAsync = RetrofitHelper.retrofitService.getArticleList(page, cid)
                val result = typeArticleListAsync?.await()
                result ?: let {
                    onTypeArticleListListener.getTypeArticleListFailed(Constant.RESULT_NULL)
                    return@async
                }
                onTypeArticleListListener.getTypeArticleListSuccess(result)
            }
        }
    }

    override fun cancelRequest() {
        typeArticleListAsync?.cancelByActive()
    }

}