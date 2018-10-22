package com.example.weiyue.component

import android.content.Context
import com.example.weiyue.MyApp
import com.example.weiyue.module.ApplicationModule
import com.example.weiyue.module.HttpModule
import com.example.weiyue.net.JanDanApi
import com.example.weiyue.net.NewsApi
import dagger.Component

@Component(modules = [(ApplicationModule::class), (HttpModule::class)])
interface ApplicationComponent {

    val application: MyApp

    val context: Context

    fun getNetEaseApi(): NewsApi

    fun getJanDanApi(): JanDanApi

}