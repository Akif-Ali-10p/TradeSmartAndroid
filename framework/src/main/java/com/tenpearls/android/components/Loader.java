package com.tenpearls.android.components;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import com.tenpearls.android.R;
import com.tenpearls.android.utilities.StringUtility;

/**
 * Provides the user interface which indicates that an operation is in progress
 * e.g. an I/O operation on network/disk. Typically, displays a
 * {@link ProgressDialog} and provides methods to interact with it.
 *
 * 
 */
public class Loader {

	/** The progress dialog. */
	private static ProgressDialog progressDialog;

	/**
	 * Shows a {@link ProgressDialog} with the provided title and description.
	 * 
	 * @param activity Activity context
	 * @param message Text to be shown as the dialog title
	 * @param title Text to be shown as the dialog message
	 */
	public static void showLoader (Activity activity, String message, String title) {

		LayoutInflater inflater = LayoutInflater.from (activity);
		View custom_view = inflater.inflate (R.layout.view_custom_progressdialog, null);
		TextView pm = (TextView) custom_view.findViewById (R.id.txtTitle);

		progressDialog = new ProgressDialog (activity);
		progressDialog.setMessage(message);
		pm.setText(message);

		if (!StringUtility.isEmptyOrNull (title)) {
			progressDialog.setTitle (title);
		}

		progressDialog.setCanceledOnTouchOutside (false);
		progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		progressDialog.show ();

		progressDialog.setContentView (custom_view);

	}

	/**
	 * Hides the {@link ProgressDialog} if being displayed. Internally handles
	 * possible exceptions.
	 */

	public static void hideLoader () {
		try {
			if (progressDialog.isShowing ())
				progressDialog.dismiss ();
		}
		catch (Exception e) {

		}

	}

	/**
	 * Returns true if Loader is showing
	 * 
	 * @return {@code true}/{@code false}
	 */
	public static boolean isLoaderShowing () {

		return progressDialog.isShowing ();
	}
}
