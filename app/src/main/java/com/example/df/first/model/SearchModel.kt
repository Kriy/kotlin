package com.example.df.first.model

import com.example.df.first.presenter.SearchPresenter

interface SearchModel {

    fun getSearchList(
            onsearchListListener: SearchPresenter.OnSearchListListener,
            page: Int = 0,
            k: String
    )

    fun cancelRequest()

    fun getLikeList(onLikeListListener: SearchPresenter.OnLikeListListener, page: Int = 0)

    fun cancelLikeListRequest()
}