package com.tenpearls.android.components;

import android.content.Context;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;
import com.tenpearls.android.utilities.StringUtility;


/**
 * Subclass of {@link android.widget.ImageView} with the added functionality of
 * seamlessly loading images from the internet.
 * 
 */
public class ImageView extends android.widget.ImageView{

	/**
	 * Instantiates a new image view.
	 * 
	 * @param context the context
	 */
	public ImageView (Context context) {

		super (context);
	}

	/**
	 * Instantiates a new image view.
	 * 
	 * @param context the context
	 * @param attrs the attrs
	 */
	public ImageView (Context context, AttributeSet attrs) {

		super (context, attrs);
	}

	/**
	 * Instantiates a new image view.
	 * 
	 * @param context the context
	 * @param attrs the attrs
	 * @param defStyle the def style
	 */
	public ImageView (Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
	}

	/**
	 * Set the default placeholder image. It will get displayed when an image is
	 * being loaded or there was an error while fetching it from the network.
	 * 
	 * @param imageResourceId Drawable resource ID
	 */

	/**
	 * Loads image from the network against the specified URL.
	 * 
	 * @param imageUrl A valid URL
	 */
	public void loadImageWithUrl (String imageUrl) {

		if(StringUtility.isEmptyOrNull(imageUrl)) {
			return;
		}

		Picasso.with(getContext())
				.load(imageUrl)
				.into(this);
	}

	public void loadImageWithUrl (String imageUrl, int defaultResourceID) {

		if(StringUtility.isEmptyOrNull(imageUrl)) {
			setImageResource(defaultResourceID);
			return;
		}

		Picasso.with(getContext())
				.load(imageUrl)
				.placeholder(defaultResourceID)
				.error(defaultResourceID)
				.into(this);
	}
}
