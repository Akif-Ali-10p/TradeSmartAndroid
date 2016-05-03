package com.tenpearls.android.utilities;



import android.content.Context;
import android.util.Log;

import com.tenpearls.android.R;


/**
 * This utility should be used for Logging purposes.
 * Its a wrapper for Android's Logging mechanism.
 *
 * <br/> Logging can be enabled or disabled using the {@code LOGGING_ENABLED}
 * boolean switch
 */
public class LogUtility {

	public static final boolean LOGGING_ENABLED = true;

	/**
	 * Log using the desired Log level with tag and message
	 * @param logLevel Log Level should be one of {@code VERBOSE, DEBUG, INFO,
	 * WARN} or {@code ERROR}
	 * @param tag
	 * @param message
	 * @param context
	 *
	 * @see LogUtility#log(int, String, String)
	 */

	public static void log (int logLevel, String tag, String message, Context context) {
		
		logByLevel (logLevel, tag, message, context);
	}

	/**
	 * Log using the desired Log level with tag and message
	 * @param logLevel Log Level should be one of {@code VERBOSE, DEBUG, INFO,
	 * WARN} or {@code ERROR}
	 * @param tag
	 * @param message
	 *
	 * @see LogUtility#log(int, String, String, Context)
	 */
	public static void log (int logLevel, String tag, String message) {

		logByLevel (logLevel, tag, message, null);
	}

	private static void logByLevel (int logLevel, String tag, String message, Context context) {

		if(!LOGGING_ENABLED) {
			return;
		}

		if(StringUtility.isEmptyOrNull(message)) {
			message = context.getResources().getString(R.string.error_message_default);
		}
		
		switch (logLevel) {

			case Log.DEBUG:
				Log.d(getTagForLogging(context.getClass(), tag), message);
				break;

			case Log.INFO:
				Log.i(getTagForLogging(context.getClass(), tag), message);
				break;

			case Log.ERROR:
				Log.e(getTagForLogging(context.getClass(), tag), message);
				break;

			case Log.WARN:
				Log.w(getTagForLogging(context.getClass(), tag), message);
				break;

			case Log.VERBOSE:
				Log.v(getTagForLogging(context.getClass(), tag), message);
				break;

			default:
				break;
		}
	}

	private static String getTagForLogging(Class<?> loggingClass, String tag) {
		if(loggingClass == null) {
			return "[" + tag + "]";
		}

		return "[" + loggingClass.getSimpleName() + "->" + tag + "]";

	}
}
