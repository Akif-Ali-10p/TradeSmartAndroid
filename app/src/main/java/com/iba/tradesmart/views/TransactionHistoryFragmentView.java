package com.iba.tradesmart.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iba.tradesmart.R;
import com.iba.tradesmart.adapters.TransactionsHistoryAdapter;
import com.iba.tradesmart.entities.Transactions;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;

/**
 * Created by on 5/5/2016.
 */
public class TransactionHistoryFragmentView extends BaseView {

    RecyclerView recyclerView;

    public TransactionHistoryFragmentView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.view_transaction_history_fragment;
    }

    @Override
    protected void onCreate() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
    }

    public void setTransactionsList(ArrayList<Transactions> transactionsList) {
        recyclerView.setAdapter(new TransactionsHistoryAdapter(transactionsList));
    }

    @Override
    protected void setActionListeners() {

    }
}
