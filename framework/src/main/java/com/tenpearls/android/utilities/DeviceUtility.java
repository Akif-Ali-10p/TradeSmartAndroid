package com.tenpearls.android.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.TypedValue;

/**
 * Utility class which is responsible for returning device specific information.
 *
 * 
 */
public class DeviceUtility {

	/** The device id. */
	private static String deviceID         = "";

	/** The os version. */
	private static String osVersion        = "";

	/** The mobile app version. */
	private static String mobileAppVersion = "";

	/**
	 * A 64-bit number (as a hex string) that is randomly generated on the
	 * device's first boot and should remain constant for the lifetime of the
	 * device. (The value may change if a factory reset is performed on the
	 * device).
	 * 
	 * @param context A valid context
	 * @return Unique device ID
	 */
	public static String getDeviceID (Context context) {

		if (StringUtility.isEmptyOrNull (deviceID)) {
			deviceID = Secure.getString (context.getContentResolver (), Secure.ANDROID_ID);
		}
		return deviceID;
	}

	/**
	 * Returns current percentage of battery left on the device.
	 * 
	 * @param context A valid context
	 * @return Battery level percentage
	 */
	public static int getBatteryLevel (Context context) {

		IntentFilter ifilter = new IntentFilter (Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.registerReceiver (null, ifilter);
		int level = batteryStatus.getIntExtra (BatteryManager.EXTRA_LEVEL, -1);
		int scale = batteryStatus.getIntExtra (BatteryManager.EXTRA_SCALE, -1);

		int batteryLevel = (int) (level / (float) scale * 100);
		return batteryLevel;
	}

	/**
	 * Gets the Android's version being run by the device.
	 * 
	 * @return Android's version number as string
	 */
	public static String getOSVersion () {

		if (StringUtility.isEmptyOrNull (osVersion)) {
			osVersion = "Android " + Build.VERSION.RELEASE;
		}
		return osVersion;
	}

	/**
	 * Gets the app's version as specified in the manifest file.
	 * 
	 * @param context A valid context
	 * @return The application's version number as string
	 */
	public static String getAppVersion (Context context) {

		if (StringUtility.isEmptyOrNull (mobileAppVersion)) {
			try {
				PackageInfo pInfo = context.getPackageManager ().getPackageInfo (context.getPackageName (), 0);
				mobileAppVersion = pInfo.versionName;
			}
			catch (NameNotFoundException e) {
				e.printStackTrace ();
			}
		}
		return mobileAppVersion;
	}

	/**
	 * Converts density independent pixels (dip) into pixels (px) according to
	 * the current device configuration.
	 * 
	 * @param dp The dp input value
	 * @param context A valid context
	 * @return Equivalent pixels value against the specified dp value.
	 */
	public static int getPixelsFromDps (int dp, Context context) {

		Resources r = context.getResources ();
		return (int) TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics ());
	}

	/**
	 * Determines the network connection state. Network can be either Wifi/Data.
	 * 
	 * @param context A valid context
	 * @return {@code true} if a trace of an active Wifi/data connection found,
	 *         {@code false} otherwise
	 */
	public static boolean isInternetConnectionAvailable (Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo ();

		if (netInfo != null && netInfo.isConnected () && netInfo.isAvailable ()) {
			return true;
		}

		return false;
	}

}
