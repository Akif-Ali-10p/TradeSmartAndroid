package com.iba.tradesmart.enums;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created on 2/15/2016.
 */
public class Enums {

    @StringDef({NAV_DRAWER_NEWS, NAV_DRAWER_TRADE, NAV_DRAWER_TRANSACTIONS, NAV_DRAWER_LOGOUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NavDrawerType {
    }

    public static final String NAV_DRAWER_NEWS = "nav_drawer_news";
    public static final String NAV_DRAWER_TRADE = "nav_drawer_trade";
    public static final String NAV_DRAWER_TRANSACTIONS = "nav_drawer_transactions";
    public static final String NAV_DRAWER_LOGOUT = "nav_drawer_logout";

    @IntDef({PROFILE_VIEW_TYPE_VALUE, PROFILE_VIEW_TYPE_NEW_SCREEN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ListProfileItemViewType {
    }

    public static final int PROFILE_VIEW_TYPE_VALUE = 0;
    public static final int PROFILE_VIEW_TYPE_NEW_SCREEN = 1;

}
