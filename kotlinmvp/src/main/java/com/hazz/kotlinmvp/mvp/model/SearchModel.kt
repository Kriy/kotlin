package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class SearchModel {

    fun requestHotWordData(): Observable<ArrayList<String>> {
        return RetrofitManager.service.getHotWord()
                .compose(SchedulerUtils.ioToMain())
    }

    fun getSearchResult(words: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getSearchData(words)
                .compose(SchedulerUtils.ioToMain())
    }

    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }

}