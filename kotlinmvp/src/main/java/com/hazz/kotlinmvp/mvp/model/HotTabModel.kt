package com.hazz.kotlinmvp.mvp.model

import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean
import com.hazz.kotlinmvp.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class HotTabModel {

    fun getTabInfo(): Observable<TabInfoBean> {
        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }

}