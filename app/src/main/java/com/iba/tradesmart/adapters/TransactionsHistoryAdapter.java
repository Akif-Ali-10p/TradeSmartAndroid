package com.iba.tradesmart.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iba.tradesmart.R;
import com.iba.tradesmart.viewholders.TransactionHistoryViewHolder;
import com.tenpearls.android.adapters.BaseLinearRecyclerAdapter;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.viewholders.BaseViewHolder;

import java.util.List;

/**
 * Created by Akif on 5/5/2016.
 */
public class TransactionsHistoryAdapter extends BaseLinearRecyclerAdapter {
    public TransactionsHistoryAdapter(List<? extends BaseEntity> entityList) {
        super(entityList);
    }

    @Override
    protected int getViewLayout(int viewType) {
        return R.layout.recycler_item_transaction;
    }

    @Override
    public BaseViewHolder getViewHolder(View view, int viewType) {
        return new TransactionHistoryViewHolder(view);
    }
}
