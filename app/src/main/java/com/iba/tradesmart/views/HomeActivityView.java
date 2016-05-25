package com.iba.tradesmart.views;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.iba.tradesmart.R;
import com.iba.tradesmart.activities.HomeActivity;
import com.iba.tradesmart.adapters.NavDrawerAdapter;
import com.iba.tradesmart.entities.NavigationDrawerItem;
import com.iba.tradesmart.enums.Enums;
import com.iba.tradesmart.interfaces.NavDrawerItemClickListener;
import com.iba.tradesmart.utils.FragmentUtils;
import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.components.TextView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;

/**
 * Created by on 5/5/2016.
 */
public class HomeActivityView extends BaseView {
    private BaseActivity mContext;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private RecyclerView recyclerViewNavDrawer;

    private TextView txtTitle;

    public HomeActivityView(Controller controller) {
        super(controller);
        mContext = getBaseActivity();
    }

    @Override
    protected int getViewLayout() {
        return R.layout.view_home_activity;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    @Override
    protected void onCreate() {
        drawer = findViewById(R.id.drawerLayout);
        txtTitle = findViewById(R.id.txtTitle);

        recyclerViewNavDrawer = findViewById(R.id.recyclerViewNavMenu);
        recyclerViewNavDrawer.setLayoutManager(new LinearLayoutManager(mContext));

        showNewsFragment();
    }

    private void showNewsFragment() {
        ((HomeActivity)mContext).showFragment(Enums.NAV_DRAWER_NEWS);
        invalidateToolBar();
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getProgressBarId() {
        return R.id.progressBar;
    }

    @Override
    protected void onToolBarSetup(Toolbar toolBar) {

        ActionBar actionBar = mContext.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setUpActionBarDrawerToggle(toolBar);
        changeNavigationDrawerIcon(toolBar);
    }

    private void setUpActionBarDrawerToggle(Toolbar toolbar) {
        toggle = new ActionBarDrawerToggle(
                mContext, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        toggle.syncState();
    }

    /**
     * Customizes the appearance of Navigation Drawer Icon
     */
    private void changeNavigationDrawerIcon(Toolbar toolbar){
        toolbar.setNavigationIcon(R.drawable.toolbar_nav_drawer);
    }

    @Override
    protected void setActionListeners() {
        drawer.setDrawerListener(toggle);

    }

    public void setNavigationDrawerItems(ArrayList<NavigationDrawerItem> listNavDrawerItems) {
        recyclerViewNavDrawer.setAdapter(new NavDrawerAdapter(listNavDrawerItems, (NavDrawerItemClickListener) controller.getBaseActivity()));
    }

    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentUtils.onBackPressed((HomeActivity) controller);
        }
    }

}
