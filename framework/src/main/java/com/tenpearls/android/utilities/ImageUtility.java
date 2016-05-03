package com.tenpearls.android.utilities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * This utility methods related to manipulating images should go inside this
 * class.
 *
 * 
 */
public class ImageUtility {

	/**
	 * Gets a bitmap object from an image resource. Also applies compression to
	 * reduce in memory size of the image.
	 * 
	 * @param resources Resources. You can get resources by doing
	 *            {@code getResources()} on a Context or Activity.
	 * @param resourceId Resource ID for the required image.
	 * @return Compressed bitmap object against the image resource.
	 */
	public static Bitmap decodeBitmapFromResource (Resources resources, int resourceId) {

		final BitmapFactory.Options options = new BitmapFactory.Options ();

		options.inScaled = false;
		options.inDither = false;
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inSampleSize = 1;
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeResource (resources, resourceId, options);
	}

}
