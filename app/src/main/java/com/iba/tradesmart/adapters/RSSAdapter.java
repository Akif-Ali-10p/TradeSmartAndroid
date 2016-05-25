package com.iba.tradesmart.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iba.tradesmart.R;
import com.iba.tradesmart.entities.FeedItem;
import com.tenpearls.android.components.TextView;
import android.view.View;

import java.util.List;

/**
 * Created by firdous on 03/05/16.
 */
public class RSSAdapter extends RecyclerView.Adapter<RSSAdapter.CustomViewHolder>{
    private List<FeedItem> feedItemList;
    private Context mContext;

    public RSSAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_news, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        FeedItem feedItem = feedItemList.get(position);
        holder.textView.setText(feedItem.getTitle());

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomViewHolder holder = (CustomViewHolder) view.getTag();
                int position = holder.getPosition();

                FeedItem feedItem = feedItemList.get(position);

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(feedItem.getUrl()));
                mContext.startActivity(i);
            }
        };

        //Handle click event on both title and image click
        holder.textView.setOnClickListener(clickListener);

        holder.textView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.title);
        }
    }
}
