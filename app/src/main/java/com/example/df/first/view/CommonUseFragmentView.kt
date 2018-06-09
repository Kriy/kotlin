package com.example.df.first.view

import com.example.df.first.bean.FriendListResponse
import com.example.df.first.bean.HotKeyResponse

interface CommonUseFragmentView {

    fun getFriendListSuccess(
            bookmarkResult: FriendListResponse?,
            commonResule: FriendListResponse,
            hotResult: HotKeyResponse
    )

    fun getFriendListFailed(errorMessage: String)

    fun getFriendListZero()
}