package com.iba.tradesmart.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.iba.tradesmart.R;
import com.iba.tradesmart.activities.base.BaseActivity;
import com.iba.tradesmart.config.AppConfig;
import com.iba.tradesmart.entities.FeedItem;
import com.iba.tradesmart.entities.NavigationDrawerItem;
import com.iba.tradesmart.enums.Enums;
import com.iba.tradesmart.fragments.NewsFragment;
import com.iba.tradesmart.fragments.TradeFragment;
import com.iba.tradesmart.fragments.TransactionHistoryFragment;
import com.iba.tradesmart.helpers.DownloadIntentService;
import com.iba.tradesmart.helpers.IllustrativeRSS;
import com.iba.tradesmart.interfaces.NavDrawerItemClickListener;
import com.iba.tradesmart.views.HomeActivityView;
import com.iba.tradesmart.views.NewsFragmentView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.utilities.UIUtility;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 5/5/2016.
 */
public class HomeActivity extends BaseActivity implements NavDrawerItemClickListener, ServiceSecondaryEventHandler {

    private static final String URL = "http://investing.einnews.com/rss/PwyIA_E3canlqJM4";
    private static final int RSS_DOWNLOAD_REQUEST_CODE = 0;

    public static List<FeedItem> list = null;

    @Override
    protected BaseView getViewForController(Controller controller) {
        return new HomeActivityView(controller);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(list != null)
            list.clear();

        HomeActivityView homeActivityView = (HomeActivityView) view;
        homeActivityView.setNavigationDrawerItems(getNavigationDrawerItems());

        PendingIntent pendingResult = createPendingResult(
                RSS_DOWNLOAD_REQUEST_CODE, new Intent(), 0);

        Intent intent = new Intent(this, DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.URL_EXTRA, URL);
        intent.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingResult);

        startService(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RSS_DOWNLOAD_REQUEST_CODE) {
            switch (resultCode) {
                case DownloadIntentService.INVALID_URL_CODE:
                    handleInvalidURL();
                    break;
                case DownloadIntentService.ERROR_CODE:
                    handleError(data);
                    break;
                case DownloadIntentService.RESULT_CODE:
                    handleRSS(data);
                    break;
            }
            handleRSS(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleRSS(Intent data) {
        IllustrativeRSS rss = data.getParcelableExtra(DownloadIntentService.RSS_RESULT_EXTRA);
        list = rss.getList();
    }

    private void handleError(Intent data) {
        // whatever you want
    }

    private void handleInvalidURL() {
        // whatever you want
    }

    @Override
    public void onBackPressed() {
        ((HomeActivityView) view).onBackPressed();
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    /**
     * Returns collection of items to be shown in navigation view
     * @return collection of navigation items
     */
    private ArrayList<NavigationDrawerItem> getNavigationDrawerItems() {

        ArrayList<NavigationDrawerItem> listNavDrawerItems = new ArrayList<>();
        String[] navDrawerItems = getResources().getStringArray(R.array.list_nav_drawer);

        for (String key : navDrawerItems) {
            listNavDrawerItems.add(getNavDrawerItem(key));
        }

        return listNavDrawerItems;
    }

    /**
     * Returns a single navigation item to be shown in collection for navigation view
     * @param key to get drawable and string resources for that particular item
     * @return single navigation item
     */
    private NavigationDrawerItem getNavDrawerItem(String key) {

        NavigationDrawerItem item = new NavigationDrawerItem();

        item.setNavItemImgResourceId(UIUtility.getResourceID(this, key, getString(R.string.string_drawable_res)));
        item.setNavItemTitle(UIUtility.getResourceID(this, key, getString(R.string.string_string_res)));
        item.setNavDrawerType(key);

        return item;
    }

    /**
     * Displays fragment by item type. If fragment is already in stack closes the navigation view
     * @param navDrawerType type of fragment to show
     */
    public void showFragment(@Enums.NavDrawerType String navDrawerType) {

        com.iba.tradesmart.utils.FragmentUtils.removeAllFragmentsFromBackStack(this);

        Fragment fragment;
        String title;

        switch (navDrawerType) {

            case Enums.NAV_DRAWER_NEWS:
                fragment = new NewsFragment();
                title = getString(R.string.nav_drawer_news);
                break;

            case Enums.NAV_DRAWER_TRADE:
                fragment = new TradeFragment();
                title = getString(R.string.nav_drawer_trade);
                break;

            case Enums.NAV_DRAWER_TRANSACTIONS:
                fragment = new TransactionHistoryFragment();
                title = getString(R.string.nav_drawer_transactions);
                break;

            default:
                fragment = new NewsFragment();
                title = getString(R.string.nav_drawer_news);
                break;
        }

        String fragmentName = fragment.getClass().getSimpleName();
        boolean isExist = com.iba.tradesmart.utils.FragmentUtils.isFragmentInStack(this, fragmentName);
        if (!isExist) {
            ((HomeActivityView) view).getTxtTitle().setText(title);
            com.iba.tradesmart.utils.FragmentUtils.pushFragment(this, R.id.contentLayout, fragment, null, fragment.getClass().getSimpleName(), false);
        }
    }

    /**
     * Callback to handle items clicked in navigation view
     * @param navDrawerType type of navigation item clicked
     */
    @Override
    public void onNavDrawerItemClick(@Enums.NavDrawerType String navDrawerType) {

        switch (navDrawerType) {
            case Enums.NAV_DRAWER_NEWS:
                showFragment(Enums.NAV_DRAWER_NEWS);
                break;

            case Enums.NAV_DRAWER_TRADE:
                showFragment(Enums.NAV_DRAWER_TRADE);
                break;

            case Enums.NAV_DRAWER_TRANSACTIONS:
                showFragment(Enums.NAV_DRAWER_TRANSACTIONS);
                break;

            case Enums.NAV_DRAWER_LOGOUT:
                logoutFromApplication();
                break;
        }

        ((HomeActivityView) view).closeDrawer();
    }

    /**
     * Cleans up user's data and log out from application
     */
    private void logoutFromApplication(){

        AppConfig.getInstance().setSocialLogin(false);
        com.iba.tradesmart.helpers.SocialNetworksHelper.googleLogout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void willStartCall() {
        showLoader();
    }

    @Override
    public void didFinishCall(boolean isSuccess) {
        hideLoader();
    }
}
