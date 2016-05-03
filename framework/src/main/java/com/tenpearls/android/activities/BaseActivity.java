package com.tenpearls.android.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;


/**
 * Abstract class which extends {@link AppCompatActivity}.
 * All activities must inherit from this class.
 *
 * @see BaseView
 */
public abstract class BaseActivity extends AppCompatActivity implements Controller {

	protected BaseView view;
	protected ServiceFactory serviceFactory;

	public static boolean isAppWentToBg             = false;
	public static boolean isWindowFocused           = false;
	public static boolean isBackPressed             = false;
	public static boolean isMovingToAnotherActivity = false;

	/**
	 * This method must return an object of a subclass of
	 * {@link BaseView}. This view will bind to this Activity through
	 * {@link Controller} interface
	 *
	 * @param controller an implementation of {@link Controller} interface
	 * @return an object of a subclass of {@link BaseView}
	 */
	public abstract BaseView getViewForController(Controller controller);


	/**
	 * A call to {@code super.onCreate()} is necessary if
	 * you override this method. It sets up the activity and has
	 * other boiler plate code
	 *
	 * @see AppCompatActivity#onCreate(Bundle)
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		initializeServiceFactory();

		initUI();
	}

	private void initializeServiceFactory()
	{
		serviceFactory = getServiceFactory();
		if(serviceFactory == null) {
			return;
		}

		try {
			serviceFactory.initialize(this);
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

	private final void initUI()
	{
		this.view = getViewForController(this);
		setContentView(this.view.getView());
		this.view.initialize();
	}


	/**
	 * This method is used to set default {@link android.support.v7.app.ActionBar}
	 * title.
	 *
	 * @return Return title {@link String}
	 * @see BaseActivity#hasToolbar()
	 * @see BaseActivity#invalidateToolBar()
	 * @see BaseView#onToolBarSetup(Toolbar)
	 */

	public String getActionBarTitle()
	{
		return "";
	}

	/**
	 * Call this method when you want to refresh
	 * {@link Toolbar}
	 *
	 * @see BaseActivity#hasToolbar()
	 * @see BaseView#onToolBarSetup(Toolbar)
	 * @see BaseActivity#getActionBarTitle()
	 */

	protected final void invalidateToolBar()
	{
		view.invalidateToolBar();
	}

	/**
	 * This method is used to know if activity has its own
	 * {@link Toolbar}
	 *
	 * @return Return {@code true} if this Activity implements its on {@link Toolbar}
	 * @see BaseActivity#invalidateToolBar()
	 * @see BaseView#onToolBarSetup(Toolbar)
	 * @see BaseActivity#getActionBarTitle()
	 */
	public boolean hasToolbar()
	{
		return false;
	}

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
	 * @see AppCompatActivity#onResume()
	 */

	@Override
	protected void onResume() {
		super.onResume();
		view.onResume();
	}

	/**
	 * Call this method to show a Not Implemented {@link Toast}
	 *
	 * Note : It is a good practice to show Not Implemented on
	 * features that are not yet ready for testing
	 *
	 */

	protected final void notImplemented()
	{
		view.notImplemented();
	}


	@Override
	public final BaseActivity getBaseActivity() {
		return this;
	}


	@Override
	protected void onStart () {
		super.onStart ();
		if (isAppWentToBg) {
			isAppWentToBg = false;
			onEnterForeground ();
		}
	}

	@Override
	protected void onStop () {
		super.onStop ();
		if (!isWindowFocused) {
			isAppWentToBg = true;
			onEnterBackground ();
		}
	}

	@Override
	public void onWindowFocusChanged (boolean hasFocus) {

		isWindowFocused = hasFocus;

		if ((isBackPressed || isMovingToAnotherActivity) && !hasFocus) {
			isBackPressed = false;
			isMovingToAnotherActivity = false;
			isWindowFocused = true;
		}

		super.onWindowFocusChanged (hasFocus);
	}

	@Override
	public void startActivity (Intent intent) {

		isMovingToAnotherActivity = true;
		super.startActivity (intent);
	}

	@Override
	public void startActivityForResult (Intent intent, int requestCode) {

		isMovingToAnotherActivity = true;
		super.startActivityForResult (intent, requestCode);
	}

	/**
	 * Override this method if you want to provide a custom implementation when
	 * application goes into background (Not very reliable).
	 */
	protected void onEnterBackground () {

		// TODO
	}


	/**
	 * Override this method if you want to provide a custom implementation when
	 * application goes into foreground (Not very reliable).
	 */
	protected void onEnterForeground () {

		// TODO
	}



}
