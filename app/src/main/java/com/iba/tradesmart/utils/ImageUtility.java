package com.iba.tradesmart.utils;

import android.content.Context;

import com.tenpearls.android.utilities.StringUtility;
import com.iba.tradesmart.R;

/**
 * Created by on 3/4/2016.
 */
public class ImageUtility {

    /**
     * This method changes the protocol of image url so that it can be redirected by Picasso
     *
     * @return redirectable url with same protocol that is required by picasso
     */
    public static String getPicassoRedirectableImageURL(Context context, String imageURL) {

        if (!StringUtility.isEmptyOrNull(imageURL)) {
            imageURL = imageURL.replace(context.getString(R.string.string_http), context.getString(R.string.string_https));
        }
        return imageURL;
    }
}
