package com.example.weiyue.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.widget.ScrollView

class MyScrollView : ScrollView {

    private lateinit var mContext: Context

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.mContext = context
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var hei = 0
        try {
            val disp = (mContext as Activity).windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            disp.getMetrics(displayMetrics)
            hei = MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels / 4, MeasureSpec.AT_MOST)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onMeasure(widthMeasureSpec, hei)
    }

}