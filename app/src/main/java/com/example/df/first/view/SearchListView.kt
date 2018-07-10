package com.example.df.first.view

import com.example.df.first.bean.HomeListResponse

interface SearchListView {

    fun getSearchListSuccess(result: HomeListResponse)

    fun getSearchListFailed(errorMessage: String?)

    fun getsearchListZero()

    fun getSearchListSmall(result: HomeListResponse)

    fun getLikeListSuccess(result: HomeListResponse)

    fun getLikeListFailed(errorMessage: String?)

    fun getLikeListZero()

    fun getLikeListSmall(result: HomeListResponse)
}