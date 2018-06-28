package com.example.df.first.presenter

import com.example.df.first.bean.FriendListResponse
import com.example.df.first.bean.HotKeyResponse
import com.example.df.first.model.HomeModel
import com.example.df.first.model.HomeModelImpl
import com.example.df.first.view.CommonUseFragmentView

class CommonUseFragmentPresenterImpl(private val commonuUserFragmentView: CommonUseFragmentView) :
        HomePresenter.OnFriendListListener {

    private val homeModel: HomeModel = HomeModelImpl()

    override fun getFriendList() {
        homeModel.getFriendList(this)
    }

    override fun getFriendListSuccess(bookmarkResult: FriendListResponse?, commonResult: FriendListResponse, hotResult: HotKeyResponse) {
        if (commonResult.errorCode != 0 || hotResult.errorCode != 0) {
            commonuUserFragmentView.getFriendListFailed(commonResult.errorMsg)
            return
        }
        if (commonResult.data == null || commonResult.data == null) {
            commonuUserFragmentView.getFriendListZero()
            return
        }
        if (commonResult.data?.size == 0 && hotResult.data?.size == 0) {
            commonuUserFragmentView.getFriendListZero()
            return
        }
        commonuUserFragmentView.getFriendListSuccess(bookmarkResult, commonResult, hotResult)
    }

    override fun getFriendListFailed(errorMessage: String?) {
        commonuUserFragmentView.getFriendListFailed(errorMessage)
    }

    fun cancelRequest(){
        homeModel.cancelFriendRequest()
    }
}