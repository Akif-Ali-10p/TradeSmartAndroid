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

		PreferenceManager.getDefaultSharedPreferences (context)
							.edit()
							.putInt(key, value)
							.apply();
	}

	/**
	 * Set a boolean value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Boolean value to be saved
	 */
	public static void putBoolean (Context context, String key, boolean value) {

		PreferenceManager.getDefaultSharedPreferences (context)
				.edit()
				.putBoolean(key, value)
				.apply();
	}

	/**
	 * Set a string value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value String value to be saved
	 */
	public static void putString (Context context, String key, String value) {

		PreferenceManager.getDefaultSharedPreferences (context)
				.edit()
				.putString (key, value)
				.apply();
	}

	/**
	 * Set a float value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Float value to be saved
	 */
	public static void putFloat (Context context, String key, float value) {

		PreferenceManager.getDefaultSharedPreferences (context)
				.edit()
				.putFloat(key, value)
				.apply();
	}

	/**
	 * Set a long value for a key in {@link SharedPreferences}.
	 * 
	 * @param context A valid context
	 * @param key Shared preferences key
	 * @param value Long value to be saved
	 */
	public static void putLong (Context context, String key, long value) {

		PreferenceManager.getDefaultSharedPreferences (context)
				.edit()
				.putLong(key, value)
				.apply();
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

		return PreferenceManager.getDefaultSharedPreferences(context)
								.getInt(key, defaultValue);
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

		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(key, defaultValue);
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

		return PreferenceManager.getDefaultSharedPreferences(context)
				.getBoolean(key, defaultValue);
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

		return PreferenceManager.getDefaultSharedPreferences(context)
				.getFloat(key, defaultValue);
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

		return PreferenceManager.getDefaultSharedPreferences(context)
				.getLong(key, defaultValue);
	}
}
