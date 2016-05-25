package com.iba.tradesmart.viewholders;

import android.view.View;

import com.iba.tradesmart.R;
import com.iba.tradesmart.entities.Transactions;
import com.tenpearls.android.components.TextView;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.viewholders.BaseViewHolder;

/**
 * Created by Akif on 5/5/2016.
 */
public class TransactionHistoryViewHolder extends BaseViewHolder {

    private final TextView txtStock;
    private final TextView txtDate;
    private final TextView txtQuantity;
    private final TextView txtType;


    public TransactionHistoryViewHolder(View itemView) {
        super(itemView);
        txtStock = (TextView) itemView.findViewById(R.id.txtStock);
        txtDate = (TextView) itemView.findViewById(R.id.txtDate);
        txtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);
        txtType = (TextView) itemView.findViewById(R.id.txtType);
    }

    @Override
    public void bind(BaseEntity entity) {
        txtStock.setText(((Transactions) entity).getStockName());
        txtDate.setText(((Transactions) entity).getDate());
        txtQuantity.setText(((Transactions) entity).getQuantity());
        txtType.setText(((Transactions) entity).getType());
    }
}
