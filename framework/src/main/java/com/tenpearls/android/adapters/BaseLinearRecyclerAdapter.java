package com.tenpearls.android.adapters;

import com.tenpearls.android.entities.BaseEntity;
import com.tenpearls.android.utilities.CollectionUtility;
import com.tenpearls.android.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khalil on 01/02/2016.
 */
public abstract class BaseLinearRecyclerAdapter<VH extends BaseViewHolder> extends BaseRecyclerAdapter<VH> {

    public BaseLinearRecyclerAdapter(List<? extends BaseEntity> entityList) {
        super(entityList);
    }

    /**
     * Call this method when data in {@code Adapter}
     * needs to be updated.
     * <br/> This method cannot be overridden. To add custom implementation,
     * override {@link BaseLinearRecyclerAdapter#willUpdateData()} and
     * {@link BaseLinearRecyclerAdapter#didUpdateData()}
     * @param list A collection of {@link BaseEntity} objects
     *
     * @see BaseLinearRecyclerAdapter#willUpdateData()
     * @see BaseLinearRecyclerAdapter#didUpdateData()
     */

    public final void updateData(List<? extends BaseEntity> list)
    {
        willUpdateData();

        int positionStart = 0;
        int size = 0;

        if (!CollectionUtility.isEmptyOrNull(entityList) && !CollectionUtility.isEmptyOrNull(list))
        {
            positionStart = entityList.size() + 1;
            size = list.size() - entityList.size();
        }

        this.entityList = list;
        if (positionStart >= size)
        {
            notifyDataSetChanged();

            return;
        }

        notifyItemRangeInserted(positionStart, size);

        didUpdateData();
    }

    protected void willUpdateData() {

    }

    protected void didUpdateData() {

    }
}
