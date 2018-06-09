package com.example.df.first.view

import com.example.df.first.bean.BannerResponse
import com.example.df.first.bean.HomeListResponse

interface HomeFrgmentView {

    fun getHomeListSuccess(result: HomeListResponse)

    fun getHomeListFailed(errorMessage: String?)

    fun getHomeListZero()

    fun getHomeListSmall(resule: HomeListResponse)

    fun getBannerSuccess(resule: BannerResponse)

    fun getBannerFailed(errorMessage: String?)

    fun getBannerZero()
}