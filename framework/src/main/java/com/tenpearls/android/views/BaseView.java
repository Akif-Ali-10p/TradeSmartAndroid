package com.tenpearls.android.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tenpearls.android.R;
import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;


/**
 * All {@code Views} for {@code Controllers} must inherit this abstract class
 *
 * @see BaseActivity
 */

public abstract class BaseView
{
    public View view;
    public Controller controller;
    private ProgressBar progressBar;
    private Toolbar toolbar;

	/*
        Life Cycle Methods
	 */

    public BaseView(Controller controller){
        super();
        this.controller = controller;
    }

    public final View getView()
    {
        if(view == null) {
            view = controller.getBaseActivity().getLayoutInflater().inflate(getViewLayout(), null);
        }
        return view;
    }

    public View getRetryView()
    {
        return null;
    }

    /**
     * Override this method if you want to
     * do something on {@code onResume} event
     *
     * Note : This method is tied to Controller's {@code onResume} event
     *
     * @see AppCompatActivity#onResume()
     * @see Fragment#onResume()
     */

    public void onResume()
    {

    }


    /**
     * This method must return the resource ID
     * of this view's layout
     *
     * @return Resource ID of layout
     */
    protected abstract int getViewLayout();

    /**
     * Do all the basic initialization code in this method
     *
     * Note : This method is tied to Controller's {@code onCreate} event
     *
     * @see AppCompatActivity#onCreate(Bundle)
     * @see Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    public abstract void onCreate();

    /**
     * Set all the Action Listeners in this method
     *
     * Note : This method is called right after {@code onCreate}
     *
     * @see BaseView#onCreate()
     */

    public abstract void setActionListeners();

    public final void initialize() {

        invalidateToolBar();
        onCreate();
        setActionListeners();
        setupProgressBar();
    }

    /**
     * Call this method when you want to refresh
     * {@link Toolbar}
     *
     * @see BaseActivity#hasToolbar()
     * @see BaseView#onToolBarSetup(Toolbar)
     * @see BaseActivity#getActionBarTitle()
     */

    public final void invalidateToolBar()
    {
        controller.getBaseActivity().supportInvalidateOptionsMenu();
        if(getToolbarId() == 0) {
            return;
        }

        boolean isAlreadyLoaded = true;

        if(toolbar == null) {
            isAlreadyLoaded = false;
            toolbar = (Toolbar) findViewById(getToolbarId());
        }

        if (toolbar == null) {
            return;
        }

        if(!controller.hasToolbar()) {
            toolbar.setVisibility(View.GONE);
            return;
        }

        toolbar.setVisibility(View.VISIBLE);

        if(!isAlreadyLoaded) {
            setupToolBar();
        }

        onToolBarRefresh(toolbar);
    }

    private final void setupToolBar()
    {
        controller.getBaseActivity().setSupportActionBar(toolbar);

        ActionBar actionBar = controller.getBaseActivity().getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(controller.getActionBarTitle());
        }

        onToolBarSetup(toolbar);
    }

    protected void onToolBarRefresh(Toolbar toolbar) {

    }

    private final void setupProgressBar() {
        if(getProgressBarId() == 0) {
            return;
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if(progressBar == null) {
            return;
        }

        onProgressBarSetup(progressBar);
    }

    protected void onProgressBarSetup(ProgressBar progressBar) {

    }

	/*
		Toast
	 */

    /**
     * Call this method to show a short {@link android.widget.Toast}
     *
     * @param text Text to show in the {@link android.widget.Toast}
     *
     * @see BaseView#showLongToast(String)
     */

    public final void showToast(String text)
    {
        Toast.makeText(controller.getBaseActivity(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Call this method to show a long {@link android.widget.Toast}
     *
     * @param text Text to show in the {@link android.widget.Toast}
     *
     * @see BaseView#showToast(String)
     */

    public final void showLongToast(String text)
    {
        Toast.makeText(controller.getBaseActivity(), text, Toast.LENGTH_LONG).show();
    }

    /*
        Helper Methods
     */

    /**
     * Call this method to show a Not Implemented {@link Toast}
     *
     * Note : It is a good practice to show Not Implemented on
     * features that are not yet ready for testing
     *
     */

    public final void notImplemented()
    {
        showToast(controller.getBaseActivity().getResources().getString(R.string.not_implemented));
    }

    /**
     * @see View#findViewById(int)
     */

    protected final <T extends View> T findViewById(int viewID)
    {
        if(view == null) {
            return null;
        }
        return (T) view.findViewById(viewID);
    }

    protected final String getString(int resID) {

        if(resID < 1) {
            return "";
        }

        return getBaseActivity().getString(resID);
    }

    /*
        Progress Loader
     */

    /**
     * Call this method to show a loader.
     * Make sure to return resource ID of a {@link ProgressBar}
     * object from the {@link BaseView#getProgressBarId()}
     *
     * <br/>Override this method to show a custom loader
     *
     * @see BaseView#hideLoader()
     * @see BaseView#getProgressBarId()
     */

    public void showLoader()
    {
        if (progressBar == null)
        {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Call this method to hide a loader.
     * Make sure to return resource ID of a {@link ProgressBar}
     * object from the {@link BaseView#getProgressBarId()}
     *
     * <br/>Override this method to hide a custom loader
     *
     * @see BaseView#showLoader()
     * @see BaseView#getProgressBarId()
     */

    public void hideLoader()
    {
        if (progressBar == null)
        {
            return;
        }

        progressBar.setVisibility(View.GONE);
    }

	/*
		Toolbar Setup
	 */

    /**
     * Return a valid resource ID of {@link Toolbar}
     * from this method
     *
     * @see BaseView#onToolBarSetup(Toolbar) ()
     * @see BaseActivity#hasToolbar()
     * @see BaseView#invalidateToolBar()
     */

    protected int getToolbarId()
    {
        return 0;
    }

    /**
     * Return a valid resource ID of {@link ProgressBar}
     * from the this method to enable the {@link BaseView#showLoader()}
     * and {@link BaseView#hideLoader()} methods
     *
     * @see BaseView#showLoader()
     * @see BaseView#hideLoader()
     */
    protected int getProgressBarId()
    {
        return 0;
    }

    /**
     * Override this method for setting up {@link Toolbar}
     * <br/> This method will be called once you return {@code true}
     * from {@link BaseActivity#hasToolbar()}
     * <br/> You also need to return a valid resource ID for
     * {@link Toolbar} in {@link BaseView#getToolbarId()}
     *
     * <br/><br/> To invoke this method, you can use {@link BaseView#invalidateToolBar()}
     *
     * @see BaseView#invalidateToolBar() ()
     * @see BaseView#getToolbarId() ()
     * @see BaseActivity#hasToolbar()
     */

    protected void onToolBarSetup(Toolbar toolBar)
    {

    }

    protected final BaseActivity getBaseActivity() {
        return controller.getBaseActivity();
    }

/*
    public void showLeftDrawer () {

        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void showRightDrawer () {

        if (drawerLayout != null) {
            drawerLayout.openDrawer (GravityCompat.END);
        }
    }

    public void closeDrawer() {

        if (drawerLayout != null) {
            drawerLayout.closeDrawers ();
        }
    }*/
}
