package com.example.multiple_status_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MultipleStatusView extends RelativeLayout {
    private static final String TAG = "MultipleStatusView";

    private static final RelativeLayout.LayoutParams DEFAULT_LAYOUT_PARAMS =
            new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);

    public static final int STATUS_CONTENT = 0x00;
    public static final int STATUS_LOADING = 0x01;
    public static final int STATUS_EMPTY = 0x02;
    public static final int STATUS_ERROR = 0x03;
    public static final int STATUS_NO_NETWORK = 0x04;

    private static final int NULL_RESOURCE_ID = -1;

    private View mEmptyView;
    private View mErrorView;
    private View mLoadingView;
    private View mNoNetworkView;
    private View mContentView;
    private int mEmptyViewResId;
    private int mErrorViewResId;
    private int mLoadingViewResId;
    private int mNoNetworkViewResId;
    private int mContentViewResId;

    private int mViewStatus;
    private LayoutInflater mInflater;
    private OnClickListener mOnRetryClickListener;

    private final ArrayList<Integer> mOtherIds = new ArrayList<>();

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultipleStatusView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MultipleStatusView, defStyleAttr, 0);
        mEmptyViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_emptyView, R.layout.empty_view);
        mErrorViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.error_view);
        mLoadingViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.loading_view);
        mNoNetworkViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_noNetworkView, R.layout.no_network_view);
        mContentViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_contentView, NULL_RESOURCE_ID);
        typedArray.recycle();
        mInflater = LayoutInflater.from(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showContent();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clear(mEmptyView, mLoadingView, mEmptyView, mNoNetworkView);
        if (mOtherIds != null) {
            mOtherIds.clear();
        }
        if (mOnRetryClickListener != null) {
            mOnRetryClickListener = null;
        }
        mInflater = null;
    }

    public int getViewStatus() {
        return mViewStatus;
    }

    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.mOnRetryClickListener = onRetryClickListener;
    }

    public final void showEmpty() {
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    public final void showEmpty(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showEmpty(inflateView(layoutId), layoutParams);
    }

    public final void showEmpty(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "Empty view is null!");
        if (mEmptyView == null) {
            mEmptyView = view;
            View emptyRetryView = mEmptyView.findViewById(R.id.empty_retry_view);
            if (mOnRetryClickListener != null && emptyRetryView != null) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mEmptyView.getId());
            addView(mEmptyView, 0, layoutParams);
        }
        showViewById(mEmptyView.getId());
    }

    public final void showError() {
        showError(mErrorViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    public final void showError(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showError(inflateView(layoutId), layoutParams);
    }

    public final void showError(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "Error view is null!");
        mViewStatus = STATUS_ERROR;
        if (mErrorView == null) {
            mErrorView = view;
            View errorRetryView = mErrorView.findViewById(R.id.error_retry_view);
            if (mOnRetryClickListener != null && errorRetryView != null) {
                errorRetryView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mErrorView.getId());
            addView(mErrorView, 0, layoutParams);
        }
        showViewById(mErrorView.getId());
    }

    public final void showLoading() {
        showLoading(mLoadingViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    public final void showLoading(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showLoading(inflateView(layoutId), layoutParams);
    }

    public final void showLoading(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "Loading view is null!");
        mViewStatus = STATUS_LOADING;
        if (mLoadingView == null) {
            mLoadingView = view;
            mOtherIds.add(mLoadingView.getId());
            addView(mLoadingView, 0, layoutParams);
        }
        showViewById(mLoadingView.getId());
    }

    public final void showNoNetwork() {
        showNoNetwork(mNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS);
    }

    public final void showNoNetwork(int layoutId, ViewGroup.LayoutParams layoutParams) {
        showNoNetwork(inflateView(layoutId), layoutParams);
    }

    public final void showNoNetwork(View view, ViewGroup.LayoutParams layoutParams) {
        checkNull(view, "No network view is null");
        mViewStatus = STATUS_NO_NETWORK;
        if (mNoNetworkView == null) {
            mNoNetworkView = view;
            View noNetworkRetryView = mNoNetworkView.findViewById(R.id.no_network_retry_view);
            if (mOnRetryClickListener != null && noNetworkRetryView != null) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener);
            }
            mOtherIds.add(mNoNetworkView.getId());
            addView(mNoNetworkView, 0, layoutParams);
        }
        showViewById(mNoNetworkView.getId());
    }

    public final void showContent() {
        mViewStatus = STATUS_CONTENT;
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater.inflate(mContentViewResId, null);
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS);
        }
        showContentView();
    }

    private void showContentView() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(mOtherIds.contains(view.getId()) ? View.GONE : View.VISIBLE);
        }
    }

    private View inflateView(int layoutId) {
        return mInflater.inflate(layoutId, null);
    }

    private void showViewById(int viewId) {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(view.getId() == viewId ? View.VISIBLE : View.GONE);
        }
    }

    private void checkNull(Object object, String hint) {
        if (null == object) {
            throw new NullPointerException(hint);
        }
    }

    private void clear(View... views) {
        if (null == views) {
            return;
        }
        try {
            for (View view : views) {
                if (null != view) {
                    removeView(view);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
