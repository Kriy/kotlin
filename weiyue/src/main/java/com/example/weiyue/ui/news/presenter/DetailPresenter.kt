package com.example.weiyue.ui.news.presenter

import com.example.weiyue.bean.NewsDetail
import com.example.weiyue.net.BaseObserver
import com.example.weiyue.net.NewsApi
import com.example.weiyue.net.NewsUtils
import com.example.weiyue.ui.base.BasePresenter
import com.example.weiyue.ui.news.contract.DetailContract
import com.example.weiyue.utils.applySchedulers
import javax.inject.Inject

class DetailPresenter @Inject
constructor(private var mNewsApi: NewsApi) : BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    override fun getData(id: String, action: String, pullNum: Int) {
        mNewsApi.getNewsDetail(id, action, pullNum)
                .applySchedulers()
                .map { t: List<NewsDetail> ->
                    t.forEach { t: NewsDetail? ->
                        if (NewsUtils.isBannerNews(t!!)) {
                            mView?.loadBannerData(t)
                        }
                        if (NewsUtils.isTopNews(t)) {
                            mView?.loadTopNewsData(t)
                        }
                    }
                    t[0]
                }
                .map { newsDetail ->
                    val iterator = newsDetail.item?.listIterator()
                    while (iterator!!.hasNext()) {
                        val it = iterator.next()
                        when (it.type) {
                            NewsUtils.TYPE_DOC -> {
                                when{
                                    it.style?.view.equals(NewsUtils.VIEW_TITLEIMG) -> it.viewType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG
                                    else -> it.viewType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG
                                }
                            }
                            NewsUtils.TYPE_ADVERT -> {
                                when{
                                    it.style != null -> when{
                                        it.style!!.view.equals(NewsUtils.VIEW_TITLEIMG) -> it.viewType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG
                                        it.style!!.view.equals(NewsUtils.VIEW_SLIDEIMG) -> it.viewType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG
                                        else -> it.viewType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG
                                    }
                                    else -> iterator.remove()
                                }
                            }
                            NewsUtils.TYPE_SLIDE -> when{
                                it.link?.type.equals("doc") -> if(it.style!!.view.equals(NewsUtils.VIEW_SLIDEIMG)){
                                    it.viewType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG
                                }else{
                                    it.viewType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG
                                }
                                else -> it.viewType = NewsDetail.ItemBean.TYPE_SLIDE
                            }
                            NewsUtils.TYPE_PHVIDEO -> {
                                it.viewType = NewsDetail.ItemBean.TYPE_PHVIDEO
                            }
                            else -> iterator.remove()
                        }
                    }
                    newsDetail.item!!
                }.compose(mView?.bindToLife<List<NewsDetail.ItemBean>>())
                .subscribe(object :BaseObserver<List<NewsDetail.ItemBean>>(){
                    override fun onSuccess(t: List<NewsDetail.ItemBean>?) {
                        when{
                            action != NewsApi.ACTION_UP -> mView?.loadData(t)
                            else -> mView?.loadMoreData(t)
                        }
                    }

                    override fun onFail(e: Throwable) {
                        when{
                            action != NewsApi.ACTION_UP -> mView?.loadData(null)
                            else -> mView?.loadMoreData(null)
                        }
                    }

                })
    }

}