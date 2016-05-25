package com.iba.tradesmart.interfaces;

import android.view.View;

import com.tenpearls.android.entities.BaseEntity;

/**
 * Created on 2/12/2016.
 */
public interface OnItemClickListener {

    void onItemClick(BaseEntity entity, View view, int position);
}
