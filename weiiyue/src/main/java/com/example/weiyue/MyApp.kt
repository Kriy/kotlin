package com.example.weiyue

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.component.DaggerApplicationComponent
import com.example.weiyue.module.ApplicationModule
import com.example.weiyue.module.HttpModule
import org.litepal.LitePal
import org.litepal.LitePalApplication
import kotlin.properties.Delegates

class MyApp : LitePalApplication() {

    companion object {
        var instance:MyApp by Delegates.notNull()
        var mApplicationComponent: ApplicationComponent by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(HttpModule())
                .build()
        //初始化数据库
        LitePal.initialize(this)
        //初始化侧滑返回组件
        BGASwipeBackManager.getInstance().init(this)
    }
}