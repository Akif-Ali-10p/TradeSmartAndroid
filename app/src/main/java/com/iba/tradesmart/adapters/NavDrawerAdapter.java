package com.iba.tradesmart.adapters;

import android.view.View;

import com.iba.tradesmart.R;
import com.iba.tradesmart.interfaces.NavDrawerItemClickListener;
import com.iba.tradesmart.viewholders.NavDrawerViewHolder;
import com.tenpearls.android.adapters.BaseLinearRecyclerAdapter;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.viewholders.BaseViewHolder;

import java.util.List;

/**
 * Created on 12/02/2016.
 */
public class NavDrawerAdapter extends BaseLinearRecyclerAdapter {

    private NavDrawerItemClickListener callback;

    public NavDrawerAdapter(List<? extends BaseEntity> entityList, NavDrawerItemClickListener callback) {
        super(entityList);
        this.callback = callback;
    }

    @Override
    public int getViewLayout(int viewType) {
        return R.layout.recycler_item_nav_drawer;
    }

    @Override
    public BaseViewHolder getViewHolder(View view, int viewType) {
        return new NavDrawerViewHolder(view, callback);
    }

}
