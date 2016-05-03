package com.tenpearls.android.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for determining collection types or states. For
 * example, a collection is null or empty.
 *
 * 
 */
public class CollectionUtility {

	/**
	 * Determines if an {@link ArrayList} is {@code null} or empty.
	 * 
	 * @param list The array list
	 * @return {@code true} if the provided string is {@code null} or empty.
	 *         {@code false} otherwise
	 */
	public static boolean isEmptyOrNull (List<?> list) {

		if (list == null)
			return true;

		return list.isEmpty();
	}
}
