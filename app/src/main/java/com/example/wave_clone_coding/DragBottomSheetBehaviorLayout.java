package com.example.wave_clone_coding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

public class DragBottomSheetBehaviorLayout extends LinearLayout {

    private final ViewDragHelper mDragHelper;
    private View mDragView;

    public DragBottomSheetBehaviorLayout(Context context) {
        this(context, null);
    }

    public DragBottomSheetBehaviorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBottomSheetBehaviorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mDragHelper = ViewDragHelper.create(this, 1.0f,callback);
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
    }

    public void setChildDragView(View view){
        mDragView = view;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);
        return true;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child==mDragView;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            //super.onEdgeDragStarted(edgeFlags, pointerId);
            mDragHelper.captureChildView(mDragView, pointerId);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            Toast.makeText(getContext(), "edgeTouched", Toast.LENGTH_SHORT).show();
        }
    };
}