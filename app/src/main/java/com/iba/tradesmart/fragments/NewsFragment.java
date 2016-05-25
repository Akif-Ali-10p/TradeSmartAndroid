package com.iba.tradesmart.fragments;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.iba.tradesmart.activities.HomeActivity;
import com.iba.tradesmart.entities.FeedItem;
import com.iba.tradesmart.fragments.base.BaseFragment;
import com.iba.tradesmart.helpers.DownloadIntentService;
import com.iba.tradesmart.helpers.IllustrativeRSS;
import com.iba.tradesmart.views.NewsFragmentView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 5/5/2016.
 */
public class NewsFragment extends BaseFragment{

//    private static final String URL = "http://investing.einnews.com/rss/PwyIA_E3canlqJM4";
//    private static final int RSS_DOWNLOAD_REQUEST_CODE = 0;

    @Override
    protected BaseView getViewForController(Controller controller) {
        return new NewsFragmentView(controller);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(HomeActivity.list != null)
            ((NewsFragmentView) view).setFeedList((ArrayList<FeedItem>) HomeActivity.list);

//        PendingIntent pendingResult = getActivity().createPendingResult(
//                RSS_DOWNLOAD_REQUEST_CODE, new Intent(), 0);
//
//        Intent intent = new Intent(getContext(), DownloadIntentService.class);
//        intent.putExtra(DownloadIntentService.URL_EXTRA, URL);
//        intent.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingResult);
//
//        getActivity().startService(intent);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RSS_DOWNLOAD_REQUEST_CODE) {
//            switch (resultCode) {
//                case DownloadIntentService.INVALID_URL_CODE:
//                    handleInvalidURL();
//                    break;
//                case DownloadIntentService.ERROR_CODE:
//                    handleError(data);
//                    break;
//                case DownloadIntentService.RESULT_CODE:
//                    handleRSS(data);
//                    break;
//            }
//            handleRSS(data);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void handleRSS(Intent data) {
//        IllustrativeRSS rss = data.getParcelableExtra(DownloadIntentService.RSS_RESULT_EXTRA);
//        List<FeedItem> list = rss.getList();
//        ((NewsFragmentView) view).setFeedList((ArrayList<FeedItem>) list);
//    }
//
//    private void handleError(Intent data) {
//        // whatever you want
//    }
//
//    private void handleInvalidURL() {
//        // whatever you want
//    }

}
