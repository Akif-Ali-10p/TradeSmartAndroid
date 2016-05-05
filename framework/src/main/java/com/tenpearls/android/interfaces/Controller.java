package com.tenpearls.android.interfaces;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.tenpearls.android.activities.BaseActivity;

/**
 * An interface to be implemented by class
 * that wish to act as Controllers for {@code Views}
 *
 * @see com.tenpearls.android.views.BaseView
 * @see BaseActivity
 * @see com.tenpearls.android.fragments.BaseFragment
 */
public interface Controller {

    BaseActivity getBaseActivity();
    String getActionBarTitle();
    boolean hasToolbar();
    View getView();
    void setSnackbar(Snackbar snackbar);
}
