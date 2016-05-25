package com.iba.tradesmart.entities;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.google.gson.JsonElement;
import com.iba.tradesmart.enums.Enums;
import com.tenpearls.android.entities.BaseEntity;

/**
 * Created  on 08/02/2016.
 */
public class NavigationDrawerItem extends BaseEntity {

    private String navDrawerType;
    private int navItemImgResourceId;
    private int navItemTitle;

    public NavigationDrawerItem(){

    }

    public NavigationDrawerItem(@Enums.NavDrawerType String navDrawerType, @DrawableRes int navItemImgResourceId, @StringRes int navItemTitle) {
        this.navDrawerType = navDrawerType;
        this.navItemImgResourceId = navItemImgResourceId;
        this.navItemTitle = navItemTitle;
    }

    @Enums.NavDrawerType
    public String getNavDrawerType() {
        return navDrawerType;
    }

    @DrawableRes
    public int getNavItemImgResourceId() {
        return navItemImgResourceId;
    }

    @StringRes
    public int getNavItemTitle() {
        return navItemTitle;
    }

    public void setNavDrawerType(String navDrawerType) {
        this.navDrawerType = navDrawerType;
    }

    public void setNavItemImgResourceId(@DrawableRes int navItemImgResourceId) {
        this.navItemImgResourceId = navItemImgResourceId;
    }

    public void setNavItemTitle(@StringRes int navItemTitle) {
        this.navItemTitle = navItemTitle;
    }

    @Override
    public void loadJson(JsonElement jsonElement) {

    }

}
