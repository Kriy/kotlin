package com.example.df.first.ui.view

import com.example.df.first.bean.BannerResponse
import com.example.df.first.bean.HomeListResponse


/**
 * Home Fragment View interface
 */
interface HomeFragmentView {
    /**
     * get Home list Success
     */
    fun getHomeListSuccess(result: HomeListResponse)

    /**
     * get Home list Failed
     */
    fun getHomeListFailed(errorMessage: String?)

    /**
     * get Home list data size equal zero
     */
    fun getHomeListZero()

    /**
     * get Home list data less than 20
     */
    fun getHomeListSmall(result: HomeListResponse)

    /**
     * get Banner Success
     * @param result BannerResponse
     */
    fun getBannerSuccess(result: BannerResponse)

    /**
     * get Banner Failed
     * @param errorMessage error message
     */
    fun getBannerFailed(errorMessage: String?)

    /**
     * get Banner data size equal zero
     */
    fun getBannerZero()
}