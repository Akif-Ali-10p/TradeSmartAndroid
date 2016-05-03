package com.tenpearls.android.utilities;

/**
 * Packages methods for string manipulation.
 *
 * 
 */
public class StringUtility {

	/**
	 * Determines if a {@link String} is {@code null} or empty.
	 * 
	 * @param string A string
	 * @return {@code true} if the provided string is {@code null} or empty.
	 *         {@code false} otherwise
	 */
	public static boolean isEmptyOrNull (String string) {

		if (string == null)
			return true;

		return (string.trim ().isEmpty());
	}

	/**
	 * Determines if the provided string is {@code null}. If {@code true}, an
	 * empty string is returned. This method can be used to avoid
	 * 
	 * @param string The string value
	 * @return If provided string is {@code null}, returns empty string. Else,
	 *         the string itself. {@link NullPointerException}s.
	 */
	public static String validateEmptyString (String string) {

		return validateEmptyString (string, "");
	}

	/**
	 * Determines if the provided string is {@code null}. If {@code true},
	 * provided defaultValue is returned. This method can be used to avoid
	 * 
	 * @param string The string value
	 * @param defaultValue the default value
	 * @return If provided string is {@code null}, returns defaultValue. Else,
	 *         the string itself. {@link NullPointerException}s.
	 */
	public static String validateEmptyString (String string, String defaultValue) {

		if (StringUtility.isEmptyOrNull (string))
			return defaultValue;

		return string;
	}

	/**
	 * Pluralizes the given string based on the count provided. For example:
	 * 
	 * @param item String entity
	 * @param itemCount The count
	 * @return Input string appended with an 's' if itemCount is more than 1
	 *         {@code
	 * StringHelper.pluralizeStringIfRequired ("trip", 1) will return "trip"
	 * StringHelper.pluralizeStringIfRequired ("trip", 2) will return "trips"
	 * }
	 */
	public static String pluralizeStringIfRequired (String item, int itemCount) {

		return (itemCount <= 1) ? item : item.concat ("s");
	}

	/**
	 * Concatenates infinite number of strings using a StringBuilder.
	 * 
	 * @param strings Arbitrary number of strings
	 * @return Concatenated string
	 */
	public static String concat (String... strings) {

		StringBuilder stringBuilder = new StringBuilder ();

		for (String string : strings) {
			stringBuilder.append (string);
		}

		return stringBuilder.toString ();
	}
}
