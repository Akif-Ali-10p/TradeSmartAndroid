package com.iba.tradesmart.viewholders;

import android.view.View;

import com.iba.tradesmart.R;
import com.iba.tradesmart.entities.NavigationDrawerItem;
import com.iba.tradesmart.interfaces.NavDrawerItemClickListener;
import com.tenpearls.android.components.ImageView;
import com.tenpearls.android.components.TextView;
import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.viewholders.BaseViewHolder;

/**
 * Created  on 08/02/2016.
 */
public class NavDrawerViewHolder extends BaseViewHolder {

    private ImageView imgNavItem;
    private TextView txtNavItem;
    private NavDrawerItemClickListener callback;
    private BaseEntity entity;

    public NavDrawerViewHolder(View itemView, NavDrawerItemClickListener callback) {
        super(itemView);
        imgNavItem = (ImageView) itemView.findViewById(R.id.imgNavItem);
        txtNavItem = (TextView) itemView.findViewById(R.id.txtNavItem);

        this.callback = callback;
    }

    @Override
    public void bind(BaseEntity entity) {

        populateNavDrawerItem((NavigationDrawerItem) entity);

        this.entity = entity;
    }

    private void populateNavDrawerItem(NavigationDrawerItem item) {
        imgNavItem.setImageResource(item.getNavItemImgResourceId());
        txtNavItem.setText(item.getNavItemTitle());
    }

    @Override
    public void onClick(View view) {
        callback.onNavDrawerItemClick(((NavigationDrawerItem) entity).getNavDrawerType());
    }

}
