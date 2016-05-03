package com.tenpearls.android.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * This class serves as an intermediate layer between {@link SharedPreferences}
 * and your code. Contains various methods to set/get persistent values in
 * Shared Preferences.
 *
 * 
 */
public class PreferenceUtility {

	/**
	 * Set an integer value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Integer value to be saved
	 */
	public static void putInteger (Context context, String key, int value) {

		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences (context).edit ();
		editor.putInt (key, value);
		editor.commit ();
	}

	/**
	 * Set a boolean value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Boolean value to be saved
	 */
	public static void putBoolean (Context context, String key, boolean value) {

		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences (context).edit ();
		editor.putBoolean (key, value);
		editor.commit ();
	}

	/**
	 * Set a string value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value String value to be saved
	 */
	public static void putString (Context context, String key, String value) {

		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences (context).edit ();
		editor.putString (key, value);
		editor.commit ();
	}

	/**
	 * Set a float value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Float value to be saved
	 */
	public static void putFloat (Context context, String key, float value) {

		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences (context).edit ();
		editor.putFloat (key, value);
		editor.commit ();
	}

	/**
	 * Set a long value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Long value to be saved
	 */
	public static void putLong (Context context, String key, long value) {

		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences (context).edit ();
		editor.putLong (key, value);
		editor.commit ();
	}

	/**
	 * Get an integer value for a key from {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key, whose value is to be retrieved
	 * @param defaultValue Default value
	 * @return The integer value against the key. If key is not set,
	 *         defaultValue will be returned
	 */
	public static int getInteger (Context context, String key, int defaultValue) {

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (context);
		return prefs.getInt (key, defaultValue);
	}

	/**
	 * Get a string value for a key from {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key, whose value is to be retrieved
	 * @param defaultValue Default value
	 * @return The string value against the key. If key is not set, defaultValue
	 *         will be returned
	 */
	public static String getString (Context context, String key, String defaultValue) {

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (context);
		return prefs.getString (key, defaultValue);
	}

	/**
	 * Get a boolean value for a key from {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key, whose value is to be retrieved
	 * @param defaultValue Default value
	 * @return The boolean value against the key. If key is not set,
	 *         defaultValue will be returned
	 */
	public static boolean getBoolean (Context context, String key, boolean defaultValue) {

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (context);
		return prefs.getBoolean (key, defaultValue);
	}

	/**
	 * Get a float value for a key from {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key, whose value is to be retrieved
	 * @param defaultValue Default value
	 * @return The float value against the key. If key is not set, defaultValue
	 *         will be returned
	 */
	public static float getFloat (Context context, String key, float defaultValue) {

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (context);
		return prefs.getFloat (key, defaultValue);
	}

	/**
	 * Get a long value for a key from {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key, whose value is to be retrieved
	 * @param defaultValue Default value
	 * @return The longu value against the key. If key is not set, defaultValue
	 *         will be returned
	 */
	public static long getLong (Context context, String key, long defaultValue) {

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (context);
		return prefs.getLong (key, defaultValue);
	}
}
