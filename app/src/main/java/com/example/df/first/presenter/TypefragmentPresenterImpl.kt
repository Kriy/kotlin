package com.example.df.first.presenter

import com.example.df.first.bean.TreeListResponse
import com.example.df.first.model.HomeModel
import com.example.df.first.model.HomeModelImpl
import com.example.df.first.view.TypeFragmentView

class TypefragmentPresenterImpl(private val typeFragmentView: TypeFragmentView) :
        HomePresenter.OnTypeTreeListListener {

    private val homeModel: HomeModel = HomeModelImpl()

    override fun getTypeTreeList() {
        homeModel.getTypeTreeList(this)
    }

    override fun getTypeTreeListSuccess(result: TreeListResponse) {
        if (result.errorCode != 0) {
            typeFragmentView.getTypeListFailed(result.errorMsg)
            return
        }
        if (result.data.isEmpty()) {
            typeFragmentView.getTypeListZero()
            return
        }
        typeFragmentView.getTypeListSuccess(result)
    }

    override fun getTypeTreeListFailed(errorMessage: String?) {
        typeFragmentView.getTypeListFailed(errorMessage)
    }

    fun cancelRequest(){
        homeModel.cancelTypeTreeRequest()
    }

}