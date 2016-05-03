package com.tenpearls.android.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class which contains method for manipulating and conversion of dates.
 *
 * 
 */
public class DateAndTimeUtility {

	/**
	 * Format the Date to given date format in the given time zone
	 * @param date
	 * @param dateFormat
	 * @param timeZone
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String format (Date date, String dateFormat, TimeZone timeZone) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat (dateFormat, Locale.getDefault ());
			simpleDateFormat.setTimeZone (timeZone == null ? TimeZone.getDefault() : timeZone);
			return simpleDateFormat.format(date);

		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * Parse the string according to the given date format
	 * in the given time zone to a {@link Date} object
	 * @param dateString
	 * @param dateFormat
	 * @param timeZone
	 * @return {@link Date object}
	 * <br/> Returns null if there is a problem
	 */

	public static final Date parse (String dateString, String dateFormat, TimeZone timeZone) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
			simpleDateFormat.setTimeZone(timeZone == null ? TimeZone.getDefault() : timeZone);
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Format the Date to given date format in the default time zone
	 * @param date
	 * @param dateFormat
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String format (Date date, String dateFormat) {

		return format(date, dateFormat, null);
	}

	/**
	 * Format the Date to given date format in the UTC time zone
	 * @param date
	 * @param dateFormat
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String formatToUTC (Date date, String dateFormat) {

		return format(date, dateFormat, getUTCTimeZone());
	}

	/**
	 * Parse the string according to the given date format
	 * in the UTC time zone to a {@link Date} object
	 * @param dateString
	 * @param dateFormat
	 * @return {@link Date object}
	 * <br/> Returns null if there is a problem
	 */

	public static final Date parseToUTCDate (String dateString, String dateFormat) {

		return parse(dateString, dateFormat, getUTCTimeZone());
	}

	/**
	 * Parse the string according to the given date format
	 * in the default time zone to a {@link Date} object
	 * @param dateString
	 * @param dateFormat
	 * @return {@link Date object}
	 * <br/> Returns null if there is a problem
	 */

	public static final Date parse (String dateString, String dateFormat) {

		return parse(dateString, dateFormat, null);
	}

	/**
	 * Convert the date string into UTC's date string
	 * with same format
	 * @param dateString
	 * @param dateFormat
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String convertToUTCString (String dateString, String dateFormat) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat (dateFormat, Locale.getDefault ());
			simpleDateFormat.setTimeZone (getUTCTimeZone ());
			Date value = simpleDateFormat.parse (dateString);
			return simpleDateFormat.format (value);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * Convert the date string into Local Timezone's date string
	 * with same format
	 * @param dateString
	 * @param dateFormat
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String convertToLocal (String dateString, String dateFormat)  {

		return parse(dateString, dateFormat, getUTCTimeZone()).toString();
	}

	/**
	 * Convert the given timestamp in milliseconds to a date string
	 * with UTC time zone in the given format
	 * @param milliSeconds
	 * @param dateFormat
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String convertMillisToUTCTime (long milliSeconds, String dateFormat) {

		return format(new Date(milliSeconds), dateFormat, getUTCTimeZone());
	}

	/**
	 * Convert the given timestamp in milliseconds to a date string
	 * with local time zone in the given format
	 * @param milliSeconds
	 * @param dateFormat
	 * @return the desired formatted date string
	 * <br/> Returns empty string if there is a problem
	 */

	public static final String convertMillisToLocalTime (long milliSeconds, String dateFormat) {

		return format(new Date(milliSeconds), dateFormat);
	}

	/**
	 * Get the number of days from Today to the
	 * given {@link Date} object
	 * @param date
	 * @return Number of days from Today
	 */

	public static final int getDaysDifferenceFromToday (Date date) {

		Calendar calendar = Calendar.getInstance ();
		calendar.setTime (date);
		int dayOfYearForDate = calendar.get (Calendar.DAY_OF_YEAR);

		calendar.setTime(new Date(System.currentTimeMillis()));
		int dayOfYearForToday = calendar.get(Calendar.DAY_OF_YEAR);

		return dayOfYearForToday - dayOfYearForDate;
	}

	/**
	 * This method returns the UTC time zone object
	 *
	 */

	public static final TimeZone getUTCTimeZone () {

		return TimeZone.getTimeZone ("UTC");
	}

}
