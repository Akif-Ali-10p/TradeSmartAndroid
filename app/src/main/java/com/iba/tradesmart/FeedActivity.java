package com.iba.tradesmart;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.components.TextView;
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
//        ViewGroup result = (ViewGroup)findViewById(R.id.results);
//        result.removeAllViews();
//        for (int i=0; i<rss.size(); i++) {
//            IllustrativeRSS.Item item = rss.get(i);
//            TextView v = new TextView(this);
//            v.setText(item.title);
//            result.addView(v);
//        }
    }

    private void handleError(Intent data) {
        // whatever you want
    }

    private void handleInvalidURL() {
        // whatever you want
    }
}
