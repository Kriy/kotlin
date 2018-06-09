package com.example.df.first.view

import com.example.df.first.bean.TreeListResponse

interface TypeFragmentView {

    fun getTypeListSuccess(result: TreeListResponse)

    fun getTypeListFailed(errorMessage: String?)

    fun getTypeListZero()
}