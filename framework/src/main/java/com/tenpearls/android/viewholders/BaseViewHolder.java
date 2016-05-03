package com.tenpearls.android.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tenpearls.android.adapters.BaseRecyclerAdapter;
import com.tenpearls.android.entities.BaseEntity;


/** An abstract class to be used for
 * creating custom View Holders for Recycler Views
 *
 * @see BaseRecyclerAdapter
 *
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public BaseViewHolder(View itemView)
    {
        super(itemView);
        if(itemView == null) {
            return;
        }
        itemView.setOnClickListener(this);
    }

    /**
     * Use this method to bind entity to the view
     * @param entity
     */
    public abstract void bind(BaseEntity entity);


    /**
     * Override this method to use the
     * click event of the {@link View}
     * @param view
     */
    @Override
    public void onClick(View view) {

    }
}
