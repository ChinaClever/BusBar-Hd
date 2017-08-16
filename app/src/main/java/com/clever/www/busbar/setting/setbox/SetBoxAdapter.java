package com.clever.www.busbar.setting.setbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clever.www.busbar.R;

import java.util.List;

/**
 * Created by Lzy on 17-8-16.
 */

public class SetBoxAdapter extends RecyclerView.Adapter<SetBoxAdapter.ViewHolder>{
    private List<SetBoxItem> mItems;

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public SetBoxAdapter(List<SetBoxItem> list) {
        mItems = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.set_box_item, parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SetBoxItem item = mItems.get(position);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
