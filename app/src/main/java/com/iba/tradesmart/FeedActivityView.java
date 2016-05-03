package com.iba.tradesmart;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;

/**
 * Created by firdous on 03/05/16.
 */
public class FeedActivityView extends BaseView {

    RecyclerView recyclerView;

    public FeedActivityView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_feed;
    }

    @Override
    public void onCreate() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(controller.getBaseActivity()));
    }

    public void setRepositoryList(ArrayList<FeedItem> repositoryList) {
        recyclerView.setAdapter(new RSSAdapter(repositoryList));
    }

    @Override
    public void setActionListeners() {

    }
}
