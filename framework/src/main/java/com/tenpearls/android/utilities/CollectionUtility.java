package com.tenpearls.android.utilities;

import com.tenpearls.android.entities.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

		return list == null || list.isEmpty();

	}

	public static String getCommaSeparatedStringFromArray(ArrayList list)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < list.size(); i++)
		{
			result.append(list.get(i));
			result.append(",");
		}

		return result.length() > 0 ? result.substring(0, result.length() - 1): "";
	}

	// Implementing Fisher–Yates shuffle
	public static void shuffleArrayList(ArrayList<? extends BaseEntity> arrayList)
	{
        long seed = System.nanoTime();
        Collections.shuffle(arrayList, new Random(seed));
	}
}
