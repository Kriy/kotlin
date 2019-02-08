package com.hazz.kotlinmvp.view.recyclerview

/**
 * Created by Terminator on 2019/2/8.
 */
interface MultipleType<in T> {
    fun getLayoutId(item: T, position: Int): Int
}