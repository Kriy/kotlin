package com.example.weiyue.ui

import android.os.Bundle
import android.view.View
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.widget.SimpleMultiStateView

interface IBase {
    /**
     * 获取页面布局 id
     */
    fun getContentLayout(): Int

    /**
     * 获取状态布局
     */
    fun getSimpleMultiStateView(): SimpleMultiStateView?

    /**
     * Dagger2 注入
     */
    fun initInjector(appComponent: ApplicationComponent)

    /**
     * 初始化布局
     */
    fun bindView(view: View, savedInstanceState: Bundle?)

    /**
     * 加载数据
     */
    fun initData()

}