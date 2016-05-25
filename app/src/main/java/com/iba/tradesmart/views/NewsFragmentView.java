package com.iba.tradesmart.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iba.tradesmart.R;
import com.iba.tradesmart.adapters.RSSAdapter;
import com.iba.tradesmart.entities.FeedItem;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;

/**
 * Created by on 5/5/2016.
 */
public class NewsFragmentView extends BaseView {
    RecyclerView recyclerView;

    public NewsFragmentView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.view_news_fragment;
    }

    @Override
    protected void onCreate() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(controller.getBaseActivity()));
    }

    public void setFeedList(ArrayList<FeedItem> repositoryList) {
        recyclerView.setAdapter(new RSSAdapter(getBaseActivity(), repositoryList));
    }

    @Override
    protected void setActionListeners() {

    }
}
