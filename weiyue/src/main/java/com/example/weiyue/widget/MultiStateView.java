package com.example.weiyue.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.weiyue.R;

public class MultiStateView extends FrameLayout {


    private static final String TAG = MultiStateView.class.getSimpleName();

    public static final int STATE_CONTENT = 10001;
    public static final int STATE_LOADING = 10002;
    public static final int STATE_EMPTY = 10003;
    public static final int STATE_FAIL = 10004;
    public static final int STATE_NONET = 10005;

    private SparseArray<View> mStateViewArray = new SparseArray<>();
    private SparseIntArray mLayoutIDArray = new SparseIntArray();
    private View mContentView;
    private int mCurrentState = STATE_CONTENT;
    private OnInflateListener mOnInflateListener;
    private OnReLoadListener mOnReLoadListener;

    public MultiStateView(@NonNull Context context) {
        this(context, null);
    }

    public MultiStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child) {
        validContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        validContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        validContentView(child);
        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, index, params);
    }

    public void setViewState(int state) {
        if (getCuttentView() == null) return;
        if (state != mCurrentState) {
            View view = getView(state);

            getCuttentView().setVisibility(GONE);
            mCurrentState = state;
            if (view != null) {
                view.setVisibility(VISIBLE);
            } else {
                int resLayoutID = mLayoutIDArray.get(state);
                if (resLayoutID == 0) return;
                view = LayoutInflater.from(getContext()).inflate(resLayoutID, this, false);
                mStateViewArray.put(state, view);
                addView(view);
                if (state == STATE_FAIL) {
                    View bt = view.findViewById(R.id.retry_bt);
                    if (bt != null) {
                        bt.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mOnReLoadListener != null) {
                                    mOnReLoadListener.onReload();
                                    setViewState(STATE_LOADING);
                                }
                            }
                        });
                    }
                    view.setVisibility(VISIBLE);
                    if (mOnInflateListener != null) {
                        mOnInflateListener.onInflate(state, view);
                    }
                }
            }
        }
    }

    public int getViewState() {
        return mCurrentState;
    }

    public View getView(int state) {
        return mStateViewArray.get(state);
    }

    public View getCuttentView() {
        if (mCurrentState == -1) return null;
        View view = getView(mCurrentState);
        if (view == null && mCurrentState == STATE_CONTENT) {
            throw new NullPointerException("content is null");
        } else if (view == null) {
            throw new NullPointerException("content state view is null, state = " + mCurrentState);
        }
        return getView(mCurrentState);
    }

    private void validContentView(View view) {
        if (isValidContentView(view)) {
            mContentView = view;
            mStateViewArray.put(STATE_CONTENT, view);
        } else if (mCurrentState != STATE_CONTENT) {
            mContentView.setVisibility(GONE);
        }
    }

    private boolean isValidContentView(View view) {
        if (mContentView == null) {
            for (int i = 0; i < mStateViewArray.size(); i++) {
                if (mStateViewArray.indexOfValue(view) != -1) return false;
            }
            return true;
        }
        return false;
    }

    public void addViewForStatus(int status, int resLayoutID) {
        mLayoutIDArray.put(status, resLayoutID);
    }

    public void setOnReLoadListener(OnReLoadListener onReLoadListener) {
        mOnReLoadListener = onReLoadListener;
    }

    public void setOnInflateListener(OnInflateListener onInflateListener) {
        mOnInflateListener = onInflateListener;
    }

    public interface OnInflateListener {
        void onInflate(int state, View view);
    }

    public interface OnReLoadListener {
        void onReload();
    }
}
