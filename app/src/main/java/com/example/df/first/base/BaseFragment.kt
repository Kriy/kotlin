package com.example.df.first.base

import android.app.Fragment

abstract class BaseFragment : Fragment() {

    protected val isFirst: Boolean = true

    protected abstract fun cancelRequest()

    override fun onDestroyView() {
        super.onDestroyView()
        cancelRequest()
    }
}