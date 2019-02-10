package com.hazz.kotlinmvp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by Terminator on 2019/2/10.
 */
class ExpandableTextView : LinearLayout, View.OnClickListener {

    private var mTextView: TextView? = null
    private var mButton: ImageView? = null
    private var mRelayout: Boolean = false
    private var mCollapsed = true

    private var mExpandDrawable: Drawable? = null
    private var mCollapseDrawable: Drawable? = null
    private var mMaxCollapsedLines: Int = 0
    private var mTextHeightWithMaxLines: Int = 0
    private var mMarginBetweenTxtAndBottom: Int = 0

    private var mCollapsedHeight: Int = 0
    private var mAnimating: Boolean = false
    private var mAnimAlphaStart: Float = 0.toFloat()
    private var mAnimationDuration: Int = 0

    private val mListener: OnExpandStateChangeListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet) {

    }

    override fun onClick(v: View?) {

    }

    internal inner class ExpandCollapseAnimation(private val mTargetView: View, private val mStartHeight: Int, private val mEndHeight: Int) : Animation() {

        init {
            duration = mAnimationDuration.toLong()
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            val newHeight = ((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight).toInt()
            mTextView!!.maxHeight = newHeight - mMarginBetweenTxtAndBottom
            if (java.lang.Float.compare(mAnimAlphaStart, 1.0f) != 0) {
                applyAlphaAnimation(mTextView, mAnimAlphaStart + interpolatedTime * (1.0f - mAnimAlphaStart))
            }
            mTargetView.layoutParams.height = newHeight
            mTargetView.requestLayout()
        }
    }

    interface OnExpandStateChangeListener {

    }

    private fun applyAlphaAnimation(view: View?, alpha: Float) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view!!.alpha = alpha
        } else {
            val alphaAnimation = AlphaAnimation(alpha, alpha)
            alphaAnimation.duration = 0
            alphaAnimation.fillAfter = true
            view!!.startAnimation(alphaAnimation)
        }
    }
}