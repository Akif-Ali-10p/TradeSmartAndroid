package com.tenpearls.android.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

/**
 * {@link Fragment}'s subclass, provides a base for implementing your fragments.
 * Always inherit from this class when using fragments.
 *
 * <br/> The class can be used as {@code Controller} for {@code Views}
 */

public abstract class BaseFragment extends Fragment implements Controller
{
    protected ServiceFactory serviceFactory;
    protected BaseView view;

    /*
        Life  cycle Methods
     */

    public BaseFragment()
    {
        super();
    }


    /**
     * A call to {@code super.onCreateView()} is necessary if
     * you override this method. It sets up the fragment and has
     * other boiler plate code
     *
     * @see Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.view = this.getViewForController(this);
        View containerView = view.getView();

        this.initializeServiceFactory();
        this.view.initialize();

        return containerView;
    }

    @Override
    public final BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    /**
     * This method is used to set default {@link android.support.v7.app.ActionBar}
     * title.
     *
     * @return Return title {@link String}
     * @see BaseFragment#hasToolbar()
     * @see BaseFragment#invalidateToolBar()
     * @see BaseView#onToolBarSetup(Toolbar)
     */

    @Override
    public String getActionBarTitle() {
        return "";
    }

    /**
     * This method is used to know if {@link Fragment} has its own
     * {@link Toolbar}
     *
     * @return Return {@code true} if this {@link Fragment} implements its on {@link Toolbar}
     * @see BaseFragment#invalidateToolBar()
     * @see BaseView#onToolBarSetup(Toolbar)
     * @see BaseFragment#getActionBarTitle()
     */

    @Override
    public boolean hasToolbar() {
        return false;
    }

    /**
     * Call this method when you want to refresh
     * {@link Toolbar}
     *
     * @see BaseFragment#hasToolbar()
     * @see BaseView#onToolBarSetup(Toolbar)
     * @see BaseFragment#getActionBarTitle()
     */

    protected final void invalidateToolBar()
    {
        view.invalidateToolBar();
    }


    @Override
    public void startActivity(Intent intent)
    {
        BaseActivity.isMovingToAnotherActivity = true;
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode)
    {
        BaseActivity.isMovingToAnotherActivity = true;
        super.startActivityForResult(intent, requestCode);
    }

    private void initializeServiceFactory()
    {
        serviceFactory = getServiceFactory();
        if(serviceFactory == null) {
            return;
        }

        try {
            serviceFactory.initialize(getBaseActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method must be overridden if this class will be
     * used to make Webservice calls.
     * Method must return an
     * object of class that inherits
     * {@link ServiceFactory}
     *
     * @return An object of class that inherits {@link ServiceFactory}
     */

    protected abstract ServiceFactory getServiceFactory();

    /**
     * This method must return an object of a subclass of
     * {@link BaseView}. This view will bind to this {@link Fragment} through
     * {@link Controller} interface
     *
     * @param controller an implementation of {@link Controller} interface
     * @return an object of a subclass of {@link BaseView}
     */

    public abstract BaseView getViewForController(Controller controller);

    /**
     * Call this method to show a loader
     *
     * @see BaseActivity#hideLoader()
     */
    protected void showLoader()
    {
        this.view.showLoader();
    }

    /**
     * Call this method to hide a loader
     *
     * @see BaseActivity#showLoader() ()
     */

    protected void hideLoader()
    {
        this.view.hideLoader();
    }

    /**
     * Call this method to show a short {@link android.widget.Toast}
     *
     * @param text Text to show in the {@link android.widget.Toast}
     *
     * @see BaseActivity#showLongToast(String)
     */

    protected final void showToast(String text)
    {
        view.showToast(text);
    }


    /**
     * Call this method to show a long {@link android.widget.Toast}
     *
     * @param text Text to show in the {@link android.widget.Toast}
     *
     * @see BaseActivity#showToast(String)
     */
    protected final void showLongToast(String text)
    {
        view.showLongToast(text);
    }

    /**
     * A call to {@code super.onResume()} is necessary if
     * you override this method
     *
     * @see Fragment#onResume()
     */

    @Override
    public void onResume()
    {
        super.onResume();
        view.onResume();
    }
}
