package com.tenpearls.android.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Encapsulates methods for UI widgets like {@link Toast}, {@link AlertDialog}
 * etc.
 *
 * 
 */
public class UIUtility {

	/**
	 * Displays an Alert dialog with a primary button.
	 * 
	 * @param title Title of the dialog
	 * @param message Descriptive message for the dialog
	 * @param positiveButtonText Button title
	 * @param context A valid context
	 */
	public static void showAlert (String title, String message, String positiveButtonText, Context context) {

		try {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (context);
			alertDialogBuilder.setTitle (title);
			alertDialogBuilder.setMessage (message);
			alertDialogBuilder.setPositiveButton (positiveButtonText, null);

			alertDialogBuilder.show ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}

	/**
	 * Hides the soft keyboard from the phone's screen.
	 * 
	 * @param editText A valid reference to any EditText, currently in the view
	 *            hierarchy
	 * @param context A valid context
	 */
	public static void hideSoftKeyboard (EditText editText, Context context) {

		InputMethodManager imm = (InputMethodManager) context.getSystemService (Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow (editText.getWindowToken (), 0);
	}

	/**
	 * Shows the soft keyboard on the phone's screen.
	 *
	 * @param editText A valid reference to any EditText, currently in the view
	 *            hierarchy
	 * @param context A valid context
	 */
	public static void showSoftKeyboard (EditText editText, Context context) {

		InputMethodManager imm = (InputMethodManager) context.getSystemService (Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput (editText, 0);
	}

	public static int getResourceID(Context context, String key, String resourceType) {
		String packageName = context.getPackageName();
		return context.getResources().getIdentifier(key, resourceType, packageName);
	}
}
