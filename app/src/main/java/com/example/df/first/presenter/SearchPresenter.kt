package com.example.df.first.presenter

import com.example.df.first.bean.HomeListResponse

interface SearchPresenter {

    interface OnSearchListListener{

        fun getSearchList(page: Int = 0, k: String)

        fun getSearchListSuccess(result: HomeListResponse)

        fun getSearchListFailed(errorMessage: String?)
    }

    interface OnLikeListListener{

        fun getLikeList(page: Int = 0)

        fun getLikeListSuccess(result: HomeListResponse)

        fun getLikeListFailed(errorMessage: String?)
    }

}