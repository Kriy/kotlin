package com.example.weiyue.ui

import android.os.Bundle
import android.view.View
import com.example.weiyue.component.ApplicationComponent
import com.example.weiyue.widget.SimpleMultiStateView

interface IBase {

    fun getContentLayout(): Int

    fun getSimpleMultiStateView(): SimpleMultiStateView?

    fun initInjector(appComponent: ApplicationComponent)

    fun bindView(view: View, savedInstanceState: Bundle?)

    fun initData()

}