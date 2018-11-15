package com.example.weiyue.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

class ObservableScrollView : ScrollView {

    var scrollViewListener: ScrollViewListener? = null

    interface ScrollViewListener {

        fun onScrollChanged(scrollView: ObservableScrollView, x: Int, y: Int, oldx: Int, oldy: Int)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        scrollViewListener?.onScrollChanged(this, l, t, oldl, oldt)
    }

}



