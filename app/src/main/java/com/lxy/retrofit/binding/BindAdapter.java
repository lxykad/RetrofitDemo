package com.lxy.retrofit.binding;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by C on 16/9/29.
 */

public class BindAdapter extends RecyclerView.Adapter<BindingHolder> {

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
