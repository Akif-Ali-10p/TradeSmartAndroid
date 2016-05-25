package com.tenpearls.badgerpoint.adapters.dividers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created on 2/12/2016.
 */

public class NavDrawerRecyclerDivider extends ItemDecoration {
    private Drawable mDivider;

    public NavDrawerRecyclerDivider(Context context, @DrawableRes int resourceId) {
        mDivider = ContextCompat.getDrawable(context, resourceId);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}