package com.iba.tradesmart.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.iba.tradesmart.helpers.DownloadIntentService;
import com.iba.tradesmart.views.FeedActivityView;
import com.iba.tradesmart.entities.FeedItem;
import com.iba.tradesmart.helpers.IllustrativeRSS;
import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.service.ServiceFactory;
import com.tenpearls.android.views.BaseView;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends BaseActivity {

//    private static final String URL = "http://www.skysports.com/rss/0,20514,11661,00.xml";
    private static final String URL = "http://investing.einnews.com/rss/PwyIA_E3canlqJM4";
    private static final int RSS_DOWNLOAD_REQUEST_CODE = 0;

    @Override
    public BaseView getViewForController(Controller controller) {
        return new FeedActivityView(controller);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PendingIntent pendingResult = createPendingResult(
                RSS_DOWNLOAD_REQUEST_CODE, new Intent(), 0);
        Intent intent = new Intent(getApplicationContext(), DownloadIntentService.class);
        intent.putExtra(DownloadIntentService.URL_EXTRA, URL);
        intent.putExtra(DownloadIntentService.PENDING_RESULT_EXTRA, pendingResult);
        startService(intent);
    }

    @Override
    protected ServiceFactory getServiceFactory() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RSS_DOWNLOAD_REQUEST_CODE) {
            switch (resultCode) {
                case DownloadIntentService.INVALID_URL_CODE:
                    handleInvalidURL();
                    break;
                case DownloadIntentService.ERROR_CODE:
                    handleError(data);
                    break;
                case DownloadIntentService.RESULT_CODE:
                    handleRSS(data);
                    break;
            }
            handleRSS(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleRSS(Intent data) {
        IllustrativeRSS rss = data.getParcelableExtra(DownloadIntentService.RSS_RESULT_EXTRA);
        List<FeedItem> list = rss.getList();
        ((FeedActivityView)view).setFeedList((ArrayList<FeedItem>) list);
    }

    private void handleError(Intent data) {
        // whatever you want
    }

    private void handleInvalidURL() {
        // whatever you want
    }

    public void navigateToNewsUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
