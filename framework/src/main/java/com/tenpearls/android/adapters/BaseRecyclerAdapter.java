package com.tenpearls.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.utilities.CollectionUtility;
import com.tenpearls.android.viewholders.BaseViewHolder;

import java.util.List;

/**
 * An abstract class for {@code Adapter} of {@link RecyclerView}
 *<br/> Should be used as a base class for {@code Adapter} of {@link RecyclerView}.
 * This class has plenty of boiler plate code to make things easy
 *
 * @see RecyclerView
 */
public abstract class BaseRecyclerAdapter<VH extends BaseViewHolder> extends RecyclerView.Adapter<VH>
{
    List<? extends BaseEntity> entityList;

    public BaseRecyclerAdapter(List<? extends BaseEntity> entityList)
    {
        this.entityList = entityList;
    }

    @Override
    public final void onBindViewHolder(VH holder, int position)
    {
        willDisplayItem(position);
        holder.bind(entityList.get(position));
    }

    /**
     * Override this method if you want to do something before
     * data is bind to Views
     * @param position position of data in list
     */

    protected void willDisplayItem(int position)
    {

    }

    /**
     * Override this method if you want to have custom
     * implementation for row count calculation
     */

    @Override
    public int getItemCount()
    {
        if (CollectionUtility.isEmptyOrNull(entityList))
        {
            return 0;
        }

        return entityList.size();
    }


    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getViewForViewHolder(getViewLayout(), parent, viewType);
        return getViewHolder(view);
    }

    /**
     * Override this method if you want to customize view inflation
     *
     * <br/> This method is called from {@link BaseRecyclerAdapter#onCreateViewHolder(ViewGroup, int)}
     *
     *  @see BaseRecyclerAdapter#getViewLayout()
     *  @see BaseRecyclerAdapter#getViewHolder(View)
     */

    protected View getViewForViewHolder(int layoutID, ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutID, parent, false);
    }

    /**
     * Return the valid resource ID of layout
     * for cell's view
     *
     * <br/> This method is called from {@link BaseRecyclerAdapter#onCreateViewHolder(ViewGroup, int)}
     *
     *  @see BaseRecyclerAdapter#getViewForViewHolder(int, ViewGroup, int)
     *  @see BaseRecyclerAdapter#getViewHolder(View)
     */
    public abstract int getViewLayout();

    /**
     * Return object of a class that inherits
     * {@link BaseViewHolder}
     *
     * <br/> This method is called from {@link BaseRecyclerAdapter#onCreateViewHolder(ViewGroup, int)}
     *
     * @param view View to be associated with the {@link android.support.v7.widget.RecyclerView.ViewHolder}
     *
     * @see BaseRecyclerAdapter#getViewForViewHolder(int, ViewGroup, int)
     * @see BaseRecyclerAdapter#getViewLayout()
     */
    public abstract VH getViewHolder(View view);
}
