package com.example.weiyue.widget

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ColorFilterImageView : AppCompatImageView, View.OnTouchListener {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN  // 按下时图像变灰
            -> setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
            MotionEvent.ACTION_UP   // 手指离开或取消操作时恢复原色
                , MotionEvent.ACTION_CANCEL -> setColorFilter(Color.TRANSPARENT)
            else -> {
            }
        }
        return false
    }
}