package com.iba.tradesmart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

public class FeedActivity extends BaseActivity {

    @Override
    public BaseView getViewForController(Controller controller) {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

}
